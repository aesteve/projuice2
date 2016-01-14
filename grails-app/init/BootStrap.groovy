import grails.util.Environment
import static grails.util.Environment.DEVELOPMENT
import projuice2.Role
import projuice2.User
import projuice2.UserRole


class BootStrap {

    def init = { servletContext ->

		Environment env = Environment.current
		if (env != DEVELOPMENT) return

        Role adminRole = new Role('ROLE_ADMIN').save()
		Role userRole = new Role('ROLE_USER').save()

		User admin = new User('me', 'password')
		admin.email = 'admin@projuice.io'
		admin.joined = new Date()
		admin.save(failOnError: true)
		UserRole.create admin, adminRole

		UserRole.withSession {
			it.flush()
			it.clear()
		}

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1

    }

    def destroy = {
    }
}
