package jpabasic.reserve.domain.collector.embed;

import jakarta.persistence.Embeddable;

@Embeddable
public class PropValue {
	
    private String value;
    
    private boolean enabled;

    protected PropValue() {}

    public PropValue(String value, boolean enabled) {
        this.value = value;
        this.enabled = enabled;
    }

    public String getValue() {
        return value;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    @Override
    public String toString() {
        return "PropValue(" +
                "value='" + value + '\'' +
                ", enabled='" + enabled + '\'' +
                ")";
    }
}