
package com.webdev_one;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import io.micrometer.common.util.StringUtils;

@Route

public class MainView extends VerticalLayout {

    /**
     * 
     */
    private static final long serialVersionUID = -3596325843924817142L;

    private TextField firstname = new TextField("First Name");
    private TextField lastname = new TextField("Last Name");
    private EmailField email = new EmailField("Email");

    private String filterText;

    private PersonRepository repository;
    private Grid<Person> grid = new Grid<>(Person.class);
    private Binder<Person> binder = new Binder<>(Person.class);

    public MainView(PersonRepository repository) {
	this.repository = repository;

	grid.setColumns("firstname", "lastname", "email");
	add(getForm(), grid);
	listContacts(filterText);

	/**
	 * add(new H1("Hello Munich")); var button = new Button("Click Here"); var
	 * textfield = new TextField(); add(new HorizontalLayout(textfield, button));
	 * button.addClickListener(e -> { add(new Paragraph("Hello " +
	 * textfield.getValue())); textfield.clear(); });
	 **/

    }

    private Component getForm() {
	var layout = new HorizontalLayout();
	layout.setAlignItems(Alignment.BASELINE);

	var addButton = new Button("Add");
	addButton.addClickShortcut(Key.ENTER); // allows pressing ENTER
	addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	addButton.addClickListener(click -> {
	    try {
		var person = new Person();
		binder.writeBean(person);
		repository.save(person);
		binder.readBean(new Person()); // clear input field after adding

		listContacts(filterText);

	    } catch (ValidationException e) {
		e.printStackTrace();
	    }
	});

	TextField filter = new TextField();
	filter.setPlaceholder("Filster by Last name");
	filter.setValueChangeMode(ValueChangeMode.EAGER);
	filter.addValueChangeListener(e -> listContacts(e.getValue()));

	layout.add(firstname, lastname, email, addButton, filter);
	binder.bindInstanceFields(this);

	return layout;
    }

    private void listContacts(String filterText) { // name changed from refreshGrid to listContacts
	if (StringUtils.isEmpty(filterText)) {
	    grid.setItems(repository.findAll());
	} else {
	    grid.setItems(repository.findBylastnameStartsWithIgnoreCase(filterText));
	}

    }

}
