package jpabasic.reserve.domain.relation.value;

public enum Position {
	
    F("공격수", "forward"), 
    D("수비수", "defender"), 
    U("심판", "umpire");
    
    private final String korName;
    
    private final String engName;
	
    Position(String korName, String engName) {
    	this.korName = korName;
    	this.engName = engName;
    }
    
    public String getKorName() {
    	return this.korName;
    }
    
    public String getEngName() {
    	return this.engName;
    }
}