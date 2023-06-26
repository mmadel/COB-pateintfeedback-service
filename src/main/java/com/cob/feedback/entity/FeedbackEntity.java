package com.cob.feedback.entity;

import com.cob.feedback.model.FeedbackQuestion;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

@Entity()
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@Table(name = "patient_feedback")
@Getter
@Setter
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private ClinicEntity clinicId;
    @Column(name = "feedback_questions", columnDefinition = "json")
    @Type(type = "json")
    private FeedbackQuestion feedbackQuestions;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "optional_feedback", length = 3000)
    private String optionalFeedback;

    private long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
