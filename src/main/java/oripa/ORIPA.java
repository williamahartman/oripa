/**
 * ORIPA - Origami Pattern Editor 
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package oripa;

import oripa.doc.Doc;
import oripa.resource.Constants;
import oripa.resource.ResourceHolder;
import oripa.resource.ResourceKey;
import oripa.resource.StringID;
import oripa.view.estimation.EstimationResultFrame;
import oripa.view.main.MainFrame;
import oripa.view.model.ModelViewFrame;
import oripa.view.model.ModelViewFrame3D;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class ORIPA {

    public static String TITLE;
    public static Doc doc;
    public static MainFrame mainFrame;
    public static ModelViewFrame modelFrame;
    public static String infoString =
			"ORIPA S: (c) 2012 OUCHI Koji\n" +
    		"http://github.com/Ooouch1\n" +
    		"ORIPA: (c) 2005-2009 Jun Mitani\nhttp://mitani.cs.tsukuba.ac.jp/\n\n"+
            "This program comes with ABSOLUTELY NO WARRANTY;\n"+
            "This is free software, and you are welcome to redistribute it\n"+
            "under certain conditions; For details check:\nhttp://www.gnu.org/licenses/gpl.html";
 
    public static ResourceBundle res;
    public static ModelViewFrame3D modelFrame3D;
    public static int tmpInt;
    public static EstimationResultFrame renderFrame;

	public static String iniFilePath = System.getProperty("user.home") + File.separator + "oripa.ini";
	private static final String resourcePackage = "oripa.resource";
	
	private static ResourceBundle createResource(String classPath){
		ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(classPath, Locale.getDefault());
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(classPath, Locale.ENGLISH);
        }

		return bundle;
	}
	
    public static void main(String[] args) {
        res = createResource(resourcePackage+".StringResource");
        ResourceHolder resources = ResourceHolder.getInstance();
        TITLE = resources.getString(ResourceKey.LABEL, StringID.Main.TITLE_ID);
        doc = new Doc(Constants.DEFAULT_PAPER_SIZE);

        setLaF(LookAndFeelFactory.getNative());
    }

	public static void setLaF(LookAndFeel laf) {
		if(mainFrame != null) {
			mainFrame.setVisible(false);
			mainFrame.dispose();
		}

		try {
			UIManager.setLookAndFeel(laf);
			createFrame();
		} catch (Exception e) {
			System.out.println("Failed to set Look and Feel: " + laf.getName());
			e.printStackTrace();
		}
	}

    private static void createFrame() {
		int uiPanelWidth = 0;
		int modelFrameWidth = 400;
		int modelFrameHeight = 400;
		int mainFrameWidth = 1000;
		int mainFrameHeight = 800;

		int appTotalWidth = mainFrameWidth + uiPanelWidth;
		int appTotalHeight = mainFrameHeight;

		// Construction of the main frame
		JFrame.setDefaultLookAndFeelDecorated(true);
		mainFrame = new MainFrame();

		Toolkit toolkit = mainFrame.getToolkit();
		Dimension dim = toolkit.getScreenSize();
		int originX = (int) (dim.getWidth() / 2 - appTotalWidth / 2);
		int originY = (int) (dim.getHeight() / 2 - appTotalHeight / 2);

		mainFrame.setBounds(originX + uiPanelWidth, originY, mainFrameWidth, mainFrameHeight);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.updateTitleText();
		mainFrame.initialize();
		mainFrame.setVisible(true);

		// Expected folded origami frame (x-ray)
		modelFrame = new ModelViewFrame();
		modelFrame.setBounds(originX + (appTotalWidth - modelFrameWidth) / 2, originY + (appTotalHeight - modelFrameHeight) / 2,
				modelFrameWidth, modelFrameHeight);
		modelFrame.setVisible(false);

		// "Folded origami" frame. Estimation of the folded form.
		renderFrame = new EstimationResultFrame();
		renderFrame.setVisible(false);

		if (Config.FOR_STUDY) {
			modelFrame3D = new ModelViewFrame3D();
			modelFrame3D.setBounds(0, 0,
					modelFrameWidth * 2, modelFrameHeight * 2);
			modelFrame3D.setVisible(true);
		}
	}

    public static void outMessage(String s) {
        JOptionPane.showMessageDialog(ORIPA.mainFrame, s, "ORIPA", JOptionPane.PLAIN_MESSAGE);
    }
}
