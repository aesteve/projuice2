package projuice2

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Project)
class ProjectSpec extends Specification {

	def setup() {}

	def cleanup() {}

	def getValidProject() {
		User testUser = new User('test', 'test@projuice.io')
		new Project(name: 'Name', description: 'Description', creationDate: new Date(), creator: testUser)
	}

	@Unroll
	void "#field is mandatory and should not be blank"() {

		setup:
		def proj = validProject
		proj[field] = value

		when: "Validating the project"
		def valid = proj.validate()

		then: "The project is not valid and has an error"
		def error = proj.errors.getFieldErrors field
		!valid && proj.hasErrors() && error

		and: "The rejected value is #value"
		error.rejectedValue == [value]

		where:
		field << ['name', 'description']
		value << [null, '']

	}

	void "Creation date should not be null"() {

		setup:
		def proj = validProject
		proj.creationDate = null

		when: "Creating a project with no creation date"
		def valid = proj.validate()

		then: "The project is not valid and has an error"
		def error = proj.errors.getFieldErrors 'creationDate'
		!valid && proj.hasErrors() && error

		and: "The rejected value is #value"
		error.rejectedValue == [null]

	}
}
