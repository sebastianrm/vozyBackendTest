/**
 * 
 */
package com.srm.vozyBackendTest.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author sebastian retamal
 *
 */
@Document(collection = "user")
public class User {
	
	@Id
	@JsonIgnore
	private ObjectId _id;
	private String userName;
	private String password;
	private String correo;
	private Boolean activo;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
}
