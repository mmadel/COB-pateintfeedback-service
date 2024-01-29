package com.cob.feedback.service.user;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.entity.UserEntity;
import com.cob.feedback.enums.UserRole;
import com.cob.feedback.excpetion.business.UserException;
import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.repository.ClinicRepository;
import com.cob.feedback.repository.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCreatorService {
    @Autowired
    UserRepository repository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    PasswordEncoder encoder;

    public UserModel create(UserModel model) throws UserException {
        Optional<UserEntity> entity = repository.getByName(model.getName());
        if (entity.isPresent())
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_IS_EXISTS,
                    new Object[]{model.getName()});
        UserEntity toBeSaved = mapUserToEntity(model);

        toBeSaved.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        UserEntity createdUser = repository.save(toBeSaved);
        model.setCreatedAt(createdUser.getCreatedAt());
        model.setId(createdUser.getId());
        return model;
    }

    public void delete(long id) {
        UserEntity tobeDeleted = repository.findById(id).get();
        tobeDeleted.setClinics(null);
        repository.delete(tobeDeleted);
    }

    public UserModel update(UserModel model) throws UserException {

        Optional<UserEntity> entity = repository.getByName(model.getName());

        UserEntity toBeUpdate = mapUserToEntity(model);
        if (entity.isPresent() && !model.getName().equals(entity.get().getName()))
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_IS_EXISTS,
                    new Object[]{model.getName()});
        if (entity.isPresent() && entity.get().getPassword().equals(model.getPassword()))
            toBeUpdate.setPassword(model.getPassword());
        else
            toBeUpdate.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        toBeUpdate.setCreatedAt(new Date().getTime());
        UserEntity updatedUser = repository.save(toBeUpdate);
        model.setCreatedAt(updatedUser.getCreatedAt());
        return model;
    }

    public void upload(XSSFWorkbook workbook) throws Exception {
        Map<String, Integer> columns = new HashMap<>();
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<UserModel> userModels = new ArrayList<>();
        int numberOfRows = sheet.getPhysicalNumberOfRows() - 1;
        sheet.getRow(0).forEach(cell -> {
            columns.put(cell.getStringCellValue(), cell.getColumnIndex());
        });
        for (int i = 1; i < numberOfRows; i++) {
            UserModel userModel = new UserModel();
            for (Map.Entry<String, Integer> entry : columns.entrySet()) {
                if (entry.getKey().equals("clinic")) {
                    List<Clinic> clinics = new ArrayList<>();
                    String name = getCellData(i, entry.getValue(), sheet);
                    Optional<ClinicEntity> clinicEntity = clinicRepository.findByName(name);
                    if(clinicEntity.isEmpty()){
                        System.out.println(name);
                    }else{
                        clinics.add(mapper.map(clinicEntity.get(), Clinic.class));
                        userModel.setClinics(clinics);
                    }
                }
                if (entry.getKey().equals("user")) {
                    userModel.setName(getCellData(i, entry.getValue(), sheet));
                }
                if (entry.getKey().equals("password")) {
                    userModel.setPassword(getCellData(i, entry.getValue(), sheet));
                }
                if (entry.getKey().equals("address")) {
                    userModel.setAddress(getCellData(i, entry.getValue(), sheet));
                }
                if (entry.getKey().equals("role")) {
                    userModel.setUserRole(UserRole.valueOf(getCellData(i, entry.getValue(), sheet)));
                }
            }
            userModels.add(userModel);
        }
        for (UserModel userModel : userModels) {
            create(userModel);
        }
    }

    private UserEntity mapUserToEntity(UserModel model) {
        //https://www.dariawan.com/tutorials/spring/illegalargumentexception-there-no-passwordencoder-mapped-id-null/
        UserEntity entity = mapper.map(model, UserEntity.class);
        entity.setClinics(getClinics(model.getClinics()));
        return entity;
    }

    private Set<ClinicEntity> getClinics(List<Clinic> clinicsModels) {
        Set<ClinicEntity> clinics = null;
        if (clinicsModels != null)
            clinics = clinicsModels.stream().map(clinicModel -> clinicRepository.findById(clinicModel.getId()).get()).collect(Collectors.toSet());
        return clinics;
    }

    private void prepareModel() {

    }

    public String getCellData(int rownum, int colnum, XSSFSheet sh) throws Exception {
        try {
            Cell cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }
}
