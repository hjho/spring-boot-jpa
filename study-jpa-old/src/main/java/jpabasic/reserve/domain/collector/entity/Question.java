package jpabasic.reserve.domain.collector.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.collector.embed.Choice;

@Entity
@Table(name = "question")
// DB TABLE: question([(id='1', text='TEST')])
// DB TABLE: question_choice_1([(question_id='1', text='C', orders=0), (question_id='1', text='A', orders=1), (question_id='1', text='B', orders=2)])
// DB TABLE: question_choice_2([(question_id='1', text='text3', inputs=1, orders=0), (question_id='1', text='text1', inputs=0, orders=1), (question_id='1', text='text2', inputs=1, orders=2)])
// Question(id='1', choices1='[C, A, B]', 
// choices2=[Choice(text='test3', inputs=true), Choice(text='test1', inputs=false), Choice(text='test2', inputs=true)])
public class Question {
    
	@Id
    private String id;
    
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "question_choice_1", joinColumns = @JoinColumn(name = "question_id"))
    @OrderColumn(name = "orders")
    @Column(name = "text")
    private List<String> choices1 = new ArrayList<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "question_choice_2", joinColumns = @JoinColumn(name = "question_id"))
    @OrderColumn(name = "orders")
    private List<Choice> choices2 = new ArrayList<>();

    protected Question() {}

    public Question(String id, String text, List<String> choices1, List<Choice> choices2) {
        this.id = id;
        this.text = text;
        this.choices1 = choices1;
        this.choices2 = choices2;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Choice> getChoices2() {
        return choices2;
    }

    public void setChoices2(List<Choice> choices2) {
        this.choices2 = choices2;
    }
    
    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", choices1='" + choices1 + '\'' +
                ", choices2=" + choices2 +
                "}";
    }
}