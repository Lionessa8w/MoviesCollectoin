/**
 * Precompiled [feature-setup.gradle.kts][Feature_setup_gradle] script plugin.
 *
 * @see Feature_setup_gradle
 */
public
class FeatureSetupPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Feature_setup_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
