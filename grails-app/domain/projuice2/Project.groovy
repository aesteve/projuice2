package projuice2

class Project {

	String name
	String description
	Date creationDate

	static constraints = {
		name blank: false
		description blank: false
	}
}
