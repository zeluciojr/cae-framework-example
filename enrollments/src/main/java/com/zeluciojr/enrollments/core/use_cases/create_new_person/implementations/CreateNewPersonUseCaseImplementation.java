package com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations;

import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.entities.Countries;
import com.zeluciojr.enrollments.core.entities.LegalId;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.CreateNewPersonUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations.ports.NewPersonPersistencePort;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations.ports.PersonRetrievalByLegalIdPort;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.io.inputs.CreateNewPersonUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.io.outputs.CreateNewPersonUseCaseOutput;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the CreateNewPersonUseCase, containing all the internal logic for its operation.
 * Since it is a FunctionUseCase, its purpose is to supply something, based on its input.
 * <p>
 * Important: Avoid hardcoding any sensitive information in this code. The auto-documentation
 * process will analyze this section of the source code if the GPT mode is enabled.
 */
@RequiredArgsConstructor
public class CreateNewPersonUseCaseImplementation extends CreateNewPersonUseCase {

    private final PersonRetrievalByLegalIdPort personRetrievalByLegalIdPort;
    private final NewPersonPersistencePort newPersonPersistencePort;

    @Override
    protected CreateNewPersonUseCaseOutput applyInternalLogic(
            CreateNewPersonUseCaseInput input,
            ExecutionContext context) {
        var country = this.getCountryOutta(input);
        var legalId = this.getLegalIdFor(country, input);
        if (legalId.isValid()){
            this.checkAvailabilityOf(legalId, context);
            var newPerson = Person.createNewOne(
                    input.getFullName(),
                    input.getPreferredName(),
                    legalId
            );
            this.save(newPerson, context);
            return new CreateNewPersonUseCaseOutput(newPerson.getId().toString());
        }
        else
            throw new InputMappedException("Invalid legal ID provided");
    }

    private Countries getCountryOutta(CreateNewPersonUseCaseInput input) {
        return Countries.of(input.getCountry())
                .orElseThrow(() -> new InputMappedException(
                        "Couldn't process the informed country",
                        "Allowed options: " + Countries.getAllowedOptionsToString()
                ));
    }

    private LegalId getLegalIdFor(Countries country, CreateNewPersonUseCaseInput input) {
        return country.getPersonalLegalIdConstructor()
                .apply(input.getLegalId());
    }

    private void checkAvailabilityOf(LegalId legalId, ExecutionContext context) {
        var personWithSameLegalId = this.personRetrievalByLegalIdPort.executePortOn(legalId, context);
        if (personWithSameLegalId.isPresent())
            throw new InputMappedException("Legal ID has been taken");
    }

    private void save(Person newPerson, ExecutionContext context) {
        this.newPersonPersistencePort.executePortOn(newPerson, context);
    }
}
