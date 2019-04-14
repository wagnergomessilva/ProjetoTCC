package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT004_ordem_producao_regra18.feature",
				plugin = {"pretty","html:target/relatorios/CT004"},
				glue = "steps/CT004",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false	
				
		)

public class RunnerCT004Test {

}
