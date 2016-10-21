package net.pablo.cei.obligatorio.server.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "users")
// JPQL
@NamedQuery(name = "findUserByName", 
query = "select u from User u where u.name = :name")
public class User implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	@OneToOne
	private Address address;
	
	@Transient
	private String noPersistir;
	
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private List<Car> cars;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Task> tareas;
 
	public List<Task> getTareas() {
		return tareas;
	}

	public void setTareas(List<Task> tareas) {
		this.tareas = tareas;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoPersistir() {
		return noPersistir;
	}

	public void setNoPersistir(String noPersistir) {
		this.noPersistir = noPersistir;
	}
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
