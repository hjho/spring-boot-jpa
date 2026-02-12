package jpabasic.reserve.domain.collector.embed;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Choice {
	
    private String text;
    
    private boolean inputs;

    protected Choice() {}

    public Choice(String text, boolean inputs) {
        this.text = text;
        this.inputs = inputs;
    }

    public String getText() {
        return text;
    }

    public boolean isInput() {
        return inputs;
    }
    
    @Override
    public String toString() {
        return "Choice(" +
                "text='" + text + '\'' +
                ", inputs=" + inputs +
                ")";
    }
}