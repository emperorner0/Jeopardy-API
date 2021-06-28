package com.example.JeopardyAPI.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private Integer showNumber;
    private String round;
    private String category;
    private Integer value;
    @Column(length = 1000)
    private String question;
    private String answer;

    public Question(Integer showNumber, String round, String category, Integer value,
                    String question, String answer) {
        this.showNumber = showNumber;
        this.round = round;
        this.category = category;
        this.value = value;
        this.question = question;
        this.answer = answer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Integer showNumber) {
        this.showNumber = showNumber;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getValue(Integer value) {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(showNumber, question1.showNumber) &&
                Objects.equals(round, question1.round) &&
                Objects.equals(category, question1.category) && Objects.equals(value, question1.value) &&
                Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, showNumber, round, category, value, question, answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", showNumber='" + showNumber + '\'' +
                ", round='" + round + '\'' +
                ", category='" + category + '\'' +
                ", value='" + value + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    static public Question parse(CSVRecord csvRecord){
        Integer showNumber = Integer.parseInt(Objects.requireNonNull(csvRecord.get(0)));
        String round = Objects.requireNonNull((csvRecord.get(1).split(" ")[0]).replace("!",""));
        String category = Objects.requireNonNull((csvRecord.get(2)));
        Integer value = Integer.parseInt(Objects
                .requireNonNull(csvRecord.get(3)
                        .replace("$","")
                        .replace(",","")
                        .replaceAll("None","0")));
        String questions = Objects.requireNonNull(csvRecord.get(4));
        String answer = Objects.requireNonNull(csvRecord.get(5));
        Question question = new Question(showNumber, round, category, value, questions, answer);
        Objects.requireNonNull(question);
        return question;
    }

}
