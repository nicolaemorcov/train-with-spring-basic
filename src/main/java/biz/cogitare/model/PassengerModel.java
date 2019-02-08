package biz.cogitare.model;

import biz.cogitare.controller.ValidationUtils;
import biz.cogitare.dto.PassengerDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passenger")
public final class PassengerModel {

    @Id
    private final String id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final AddressModel addressModel;


    public PassengerModel(String id, String firstName, String lastName, int age, AddressModel addressModel) {
        this.id = ValidationUtils.isNotNullAndNotEmpty(id, "passenger ID should not be empty");
        this.firstName = ValidationUtils.isNotNullAndNotEmpty(firstName, "firstName should not be empty");
        this.lastName = ValidationUtils.isNotNullAndNotEmpty(lastName, "lastName should not be empty");
        this.age = ValidationUtils.checkMinValue(age, 10, "You must be minimum 10 Years old");
        this.addressModel = ValidationUtils.isNotNull(addressModel, "Address must not be null");
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public PassengerDto convertToPassengerDto(){

        PassengerDto passengerDto = new PassengerDto(
                this.id,
                this.firstName,
                this.lastName,
                this.age,
                this.addressModel.convertToAddressDto()
        );
        return passengerDto;
    }

    @Override
    public String toString() {
        return "PassengerModel{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", addressModel=" + addressModel +
                '}';
    }
}
