package kr.ac.shinhan.csp;

import javax.jdo.annotations.IdGeneratorStrategy;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;




@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TeamMember {

	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;	
	
	@Persistent
	private String name;		
	@Persistent
	private String socialNum;	
	@Persistent
	private String tel;
	@Persistent
	private String mail;
	@Persistent
	private String katok;	
	@Persistent
	private boolean check;
	@Persistent
	private String gitid;	
	
	public TeamMember(String name, String socialNum,String tel,String mail,String katok,boolean check,String gitid)
	{
		this.name= name;
		this.socialNum = socialNum;
		this.tel = tel;
		this.mail = mail;
		this.katok = katok;
		this.check = check;
		this.gitid = gitid;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialNum() {
		return socialNum;
	}

	public void setSocialNum(String socialNum) {
		this.socialNum = socialNum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getKatok() {
		return katok;
	}

	public void setKatok(String katok) {
		this.katok = katok;
	}
	

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getGitid() {
		return gitid;
	}

	public void setGitid(String gitid) {
		this.gitid = gitid;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	
	
}
