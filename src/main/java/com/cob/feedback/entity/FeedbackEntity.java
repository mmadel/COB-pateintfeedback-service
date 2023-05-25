package com.cob.feedback.entity;

import com.cob.feedback.model.FeedbackItem;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity()
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@Table(name = "feedback")
@Getter
@Setter
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private ClinicEntity clinicId;
    @Column(name = "feedback_items", columnDefinition = "json" )
    @Type(type = "json")
    private List<FeedbackItem> items;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "optional_feedback" , length = 3000)
    private String optionalFeedback;

    private long createdAt;
    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
