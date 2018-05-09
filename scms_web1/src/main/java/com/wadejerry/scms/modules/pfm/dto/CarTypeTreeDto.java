package com.wadejerry.scms.modules.pfm.dto;

public class CarTypeTreeDto {


	private Integer id;

	public boolean getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked ="true".equals(checked)?true:false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Integer pId;
	private boolean checked;
	private String name;
	private boolean chkDisabled=false;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

}
