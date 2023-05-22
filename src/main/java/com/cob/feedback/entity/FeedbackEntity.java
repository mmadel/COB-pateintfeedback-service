package com.cob.feedback.entity;

import com.cob.feedback.model.FeedbackItem;
import com.cob.feedback.model.OptionalFeedback;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity()
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
    @Column(name = "optional_feedback", columnDefinition = "json")
    @Type(type = "json")
    private OptionalFeedback optionalFeedback;
    @Column(name = "feedback_items", columnDefinition = "json")
    @Type(type = "json")
    private List<FeedbackItem> items;
    @Column(name = "feedback_value")
    private String feedbackValue;
}
