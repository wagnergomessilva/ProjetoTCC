package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT002_ordem_producao_regra02.feature",
				plugin = "pretty",
				glue = "steps",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false				
		)

public class RunnerOrdemDeProducaoTest {
	
}
