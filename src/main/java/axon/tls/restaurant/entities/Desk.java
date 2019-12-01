package axon.tls.restaurant.entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;

import axon.tls.restaurant.config.Constants;

@Entity
@Table(name = "desks")
@JsonFilter(value = Constants.DESK_FILTER)
public class Desk extends AuditModel<String> {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "floor_id", nullable = false)
	private Floor floor;

	private int size;

	private String name;

	@Enumerated(EnumType.STRING)
	private DeskState state;

	public Desk(Desk desk) {
		this.size = desk.size;
		this.name = desk.name;
		this.state = desk.state;
	}


	public Desk() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeskState getState() {
		return state;
	}

	public void setState(DeskState state) {
		this.state = state;
	}

}
