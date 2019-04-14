package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT015_ordem_producao_regra16.feature",
				plugin = {"pretty","html:target/relatorios/CT015"},
				glue = "steps/CT015",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false	
		)

public class RunnerCT015Test {
	
}
