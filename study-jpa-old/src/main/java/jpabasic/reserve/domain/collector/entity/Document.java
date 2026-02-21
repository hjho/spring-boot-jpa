package jpabasic.reserve.domain.collector.entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.collector.embed.PropValue;

@Entity
@Table(name = "documents")
// DB TABLE: documents{id='1', title='test', content='content'}
// DB TABLE: documents_prop_1[{documents_id='1', name='headYn', value='Y'}, {documents_id='1', name='ageYn', value='Y'}]
// DB TABLE: documents_prop_2[{documents_id='1', name='moneyYn', value='N', enabled=0}, {documents_id='1', name='healthYn', value='N', enabled=0}, {documents_id='1', name='timeYn', value='N', enabled=0}]
// Document{id='1', title='test', content=content, props1={headYn=Y, ageYn=Y}, 
// props2={moneyYn=PropValue(value='N', enabled='false'), healthYn=PropValue(value='N', enabled='false'), timeYn=PropValue(value='N', enabled='false')}}
public class Document {
    @Id
    private String id;
    private String title;
    private String content;
    
    @ElementCollection
    @CollectionTable(name = "documents_prop_1", joinColumns = @JoinColumn(name = "documents_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> props1 = new HashMap<>();
    
    @ElementCollection
    @CollectionTable(name = "documents_prop_2", joinColumns = @JoinColumn(name = "documents_id"))
    @MapKeyColumn(name = "name")
    private Map<String, PropValue> props2 = new HashMap<>();

    protected Document() {}

    public Document(String id, String title, String content, Map<String, String> props1, Map<String, PropValue> props2) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.props1 = props1;
        this.props2 = props2;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    
    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", props1=" + props1 +
                ", props2=" + props2 +
                "}";
    }
}