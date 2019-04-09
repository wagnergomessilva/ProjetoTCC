package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT017_ordem_producao_regra19.feature",
				plugin = "pretty",
				glue = "steps/CT017",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false	
		)

public class RunnerCT017Test {
	
}
