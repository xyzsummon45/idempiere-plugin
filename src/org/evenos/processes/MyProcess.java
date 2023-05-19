package org.evenos.processes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.util.Callback;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class MyProcess extends SvrProcess {

	private String aString;
	private boolean acheckbox;
	private Timestamp adateTime;
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
		log.warning("--- Here I'm in the prepare()-method");

		ProcessInfoParameter[] paras = getParameter();
		for(ProcessInfoParameter para : paras) {
			String paraName = para.getParameterName();
			
			if(paraName.equalsIgnoreCase("astring")) {
				aString = para.getParameterAsString();
			}else if(paraName.equalsIgnoreCase("acheckbox")){
				acheckbox = para.getParameterAsBoolean();
			}else if(paraName.equalsIgnoreCase("adatetime")){
				adateTime = para.getParameterAsTimestamp();
			}else{
				log.log(Level.SEVERE, "unknown Parameter: "+ paraName);
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		log.warning("--- Here I'm in the dolt()-method");
		
		addLog(aString);
		addLog(1000000, adateTime, BigDecimal.ONE , "Our DateTime is " + adateTime.toString());
		addLog(1000001, adateTime, BigDecimal.ONE , "Our Checkbox is " + acheckbox, 100, 100);

		final StringBuilder yesno = new StringBuilder();
		
		processUI.ask("Yes or No?", new Callback<Boolean>() {
			
			@Override
			public void onCallback(Boolean result) {
				// TODO Auto-generated method stub
				yesno.append(result);
			}
		});
		
		while(yesno.length() == 0)
		{
			log.warning("warning");
		}
		
		final StringBuilder string = new StringBuilder();
		final StringBuilder stringProvided = new StringBuilder();
		
		processUI.askForInput("Please enter a String", new Callback<String>() {

			@Override
			public void onCallback(String result) {
				// TODO Auto-generated method stub
				stringProvided.append(true);
				string.append(result);
			}
		});
		
		while(string.length() == 0)
		{
			log.warning("warning");
		}
		
		addLog(yesno.toString());
		addLog(string.toString());
		
		return null;
	}

}
