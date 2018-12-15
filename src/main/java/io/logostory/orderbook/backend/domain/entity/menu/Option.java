package io.logostory.orderbook.backend.domain.entity.menu;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option")
public class Option {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long price;

	@ManyToOne(optional=false)
	@JoinColumn(name="menu")
	@JsonIgnore
	private Menu menu;
}
