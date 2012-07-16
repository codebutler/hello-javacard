# Hello JavaCard Applet

More info here later.

## Compile

Compile with IBM JCOP Tools for Eclipse.

## Install onto SIM card

Install using the [SIM Alliance Loader v2](http://www.simalliance.org/).

1. Right-click on "Root", "Add Node" -> "Add OTA Session"
    * Send Terminal Profile: True
	
	1. Right-click on OTASession, "Add Package"
		* FileName: (choose cap file)
		* Security domain AID: A000000070000501495344

	2. Right-click on OTASession, "Add Applet STK"
		* Package AID: D0D1D2D3D4D601
		* AID: D0D1D2D3D4D60101
		* Instance AID: D0D1D2D3D4D60101
		* Max menu entries: 01
		* Menu entries: 0001

2. OTA Configuration (toolbar button)
	* SMS
		* Originating Address / Service centre
			* Type of number: International
			* NPI: National
			* Number: 55667788


