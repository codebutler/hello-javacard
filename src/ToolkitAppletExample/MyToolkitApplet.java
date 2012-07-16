/**
 * Example of Toolkit Applet
 */

package ToolkitAppletExample;

import sim.toolkit.*;
import sim.access.*;
import javacard.framework.*;

public class MyToolkitApplet extends javacard.framework.Applet implements
		ToolkitInterface, ToolkitConstants {
	// Mandatory variables
	private SIMView gsmFile;
	private ToolkitRegistry reg;

	// Main Menu
	private byte idMenu1;
	private byte[] Menu1;

	public MyToolkitApplet() {		
		gsmFile = SIMSystem.getTheSIMView();
		
		reg = ToolkitRegistry.getEntry();

		Menu1 = new byte[] {
				(byte) 'D', (byte) 'i', (byte) 's', (byte) 'p',
				(byte) 'l', (byte) 'a', (byte) 'y', (byte) ' ',
				(byte) 'T', (byte) 'e', (byte) 'x', (byte) 't' };
		
		idMenu1 = reg.initMenuEntry(Menu1, (short) 0, (short) Menu1.length,
				PRO_CMD_SELECT_ITEM, false, (byte) 0, (short) 0);
		 
	}

	/**
	 * Method called by the JCRE at the installation of the applet
	 * @param bArray the byte array containing the AID bytes
	 * @param bOffset the start of AID bytes in bArray
	 * @param bLength the length of the AID bytes in bArray
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		// Create the Java SIM toolkit applet
		MyToolkitApplet StkCommandsExampleApplet = new MyToolkitApplet();

		// Register this applet
		StkCommandsExampleApplet.register(bArray, (short) (bOffset + 1),
				(byte) bArray[bOffset]);
		
	}

	/**
	 * Method called by the SIM Toolkit Framework
	 * @param event the byte representation of the event triggered
	 */
	public void processToolkit(byte event) {
		EnvelopeHandler envHdlr = EnvelopeHandler.getTheHandler();

		// Manage the request following the MENU SELECTION event type
		if (event == EVENT_MENU_SELECTION) {
			// Get the selected item
			byte selectedItemId = envHdlr.getItemIdentifier();

			// Perform the required service following the Menu1 selected item
			if (selectedItemId == idMenu1) {
				menu1Action();
			}
		}
	}

	/**
	 * Method called by the JCRE, once selected
	 * @param apdu the incoming APDU object
	 */
	public void process(APDU apdu) {
		// ignore the applet select command dispached to the process
		if (selectingApplet()) {
			return;
		}
		
		//byte[] cmd_apdu = apdu.getBuffer();
		
		//switch (cmd_apdu[ISO7816.OFFSET_INS]) {
		//case 0x01:
			// Get the GSM application reference
			
			//break;
		//}
		
		//apdu.setOutgoing();
	}

	/**
	 * Manage the Menu1 selection
	 */
	private void menu1Action() {
		// Get the received envelope
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();

		// Display the "Menu1" message text
		// Initialize the display text command
		proHdlr.initDisplayText((byte) 0x00, DCS_8_BIT_DATA, Menu1, (short) 0,
					(short) (Menu1.length));
		proHdlr.send();
		return;
	}
}