package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT012_ordem_producao_regra13.feature",
				plugin = {"pretty","html:target/relatorios/CT012"},
				glue = "steps/CT012",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false	
		)

public class RunnerCT012Test {
	
}
