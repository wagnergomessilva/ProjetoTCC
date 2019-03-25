package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import runners.RunnerOrdemDeProducaoTest;
import runners.RunnerOutrasNotasFiscaisTest;


@RunWith(Suite.class)
@SuiteClasses({	
	RunnerOrdemDeProducaoTest.class,
	RunnerOutrasNotasFiscaisTest.class
})
public class SuiteTestes {

}
