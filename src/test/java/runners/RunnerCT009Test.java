package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT009_ordem_producao_regra9.feature",
				plugin = {"pretty","html:target/relatorios/CT009"},
				glue = "steps/CT009",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false	
		)

public class RunnerCT009Test {
	
}
