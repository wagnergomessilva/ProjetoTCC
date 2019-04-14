package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/resources/features/CT001_ordem_producao_regra06.feature",
				plugin = {"pretty","html:target/relatorios/CT001"},
				glue = "steps/CT001",
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false,
				strict = false	
				
		)

public class RunnerCT001Test {
	
}
