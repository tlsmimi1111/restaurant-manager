package axon.tls.restaurant.entities;

import org.hibernate.annotations.NaturalId;

import axon.tls.constant.RoleName;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "role_name", length = 60)
	private RoleName name;

	public RoleEntity() {
	}

	public RoleEntity(RoleName name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
}