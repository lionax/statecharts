/**
* Copyright (c) 2015 committers of YAKINDU and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     committers of YAKINDU - initial API and implementation
*/
package org.yakindu.sct.simulation.core.sexec.test;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sexec.interpreter.test.util.AbstractExecutionFlowTest;
import org.yakindu.sct.model.sexec.interpreter.test.util.SExecInjectionProvider;
import org.yakindu.sct.test.models.SCTUnitTestModels;
import com.google.inject.Inject;
import static org.junit.Assert.assertTrue;
/**
 *  Unit TestCase for AfterCycles
 */
@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(SExecInjectionProvider.class)
public class AfterCyclesTest extends AbstractExecutionFlowTest {
	@Before
	public void setup() throws Exception {
		ExecutionFlow flow = models.loadExecutionFlowFromResource("AfterCycles.sct");
		initInterpreter(flow);
	}
	@Test
	public void AftrerCyclesTest() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		interpreter.runCycle();
		interpreter.runCycle();
		assertTrue(isStateActive("A"));
		interpreter.runCycle();
		assertTrue(isStateActive("B"));
		interpreter.runCycle();
		assertTrue(getInteger("myInt") == 0l);
		interpreter.runCycle();
		assertTrue(getInteger("myInt") == 1l);
		assertTrue(getInteger("anotherInt") == 5l);
		interpreter.runCycle();
		interpreter.runCycle();
		assertTrue(getInteger("myInt") == 2l);
		interpreter.runCycle();
		interpreter.runCycle();
		interpreter.runCycle();
		assertTrue(getInteger("anotherInt") == 10l);
		assertTrue(getBoolean("mybool") == false);
	}
}
