package oripa;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.painter.fill.MatteFillPainter;
import org.pushingpixels.substance.api.painter.highlight.ClassicHighlightPainter;
import org.pushingpixels.substance.api.shaper.StandardButtonShaper;
import org.pushingpixels.substance.api.skin.GeminiSkin;
import org.pushingpixels.substance.api.skin.GraphiteAquaSkin;
import org.pushingpixels.substance.api.skin.TwilightSkin;
import org.pushingpixels.substance.internal.painter.SimplisticSoftBorderPainter;

import javax.swing.*;

public class LookAndFeelFactory {
	public static LookAndFeel getOripaGemini() {
		return new SubstanceLookAndFeel(new OripaGemini()){};
	}

	public static LookAndFeel getOripaTwilight() {
		return new SubstanceLookAndFeel(new OripaTwilight()){};
	}

	public static LookAndFeel getOripaGraphite() {
		return new SubstanceLookAndFeel(new OripaGraphite()){};
	}

	public static LookAndFeel getNative() {
		try {
			Class<?> laf = Class.forName(UIManager.getSystemLookAndFeelClassName());
			return (LookAndFeel) laf.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.err.println("Could not load native skin.");
			e.printStackTrace();
			return null;
		}
	}

	/*
	SKIN DEFINITIONS
	 */

	private static class OripaGemini extends GeminiSkin {
		public OripaGemini() {
			this.borderPainter = new SimplisticSoftBorderPainter();
			this.highlightBorderPainter = new SimplisticSoftBorderPainter();
			this.fillPainter = new MatteFillPainter();
			this.highlightPainter = new ClassicHighlightPainter();
			this.buttonShaper = new StandardButtonShaper() {
				@Override
				public float getCornerRadius(AbstractButton button, float insets) {
					return 0;
				}
			};
		}
		public String getDisplayName() {
			return "OripaGemini";
		}
	}

	private static class OripaTwilight extends TwilightSkin {
		public OripaTwilight() {
			this.borderPainter = new SimplisticSoftBorderPainter();
			this.highlightBorderPainter = new SimplisticSoftBorderPainter();
			this.fillPainter = new MatteFillPainter();
			this.highlightPainter = new ClassicHighlightPainter();
			this.buttonShaper = new StandardButtonShaper() {
				@Override
				public float getCornerRadius(AbstractButton button, float insets) {
					return 0;
				}
			};
		}
		public String getDisplayName() {
			return "OripaTwilight";
		}
	}

	private static class OripaGraphite extends GraphiteAquaSkin {
		public OripaGraphite() {
			this.borderPainter = new SimplisticSoftBorderPainter();
			this.highlightBorderPainter = new SimplisticSoftBorderPainter();
			this.fillPainter = new MatteFillPainter();
			this.highlightPainter = new ClassicHighlightPainter();
			this.buttonShaper = new StandardButtonShaper() {
				@Override
				public float getCornerRadius(AbstractButton button, float insets) {
					return 0;
				}
			};
		}
		public String getDisplayName() {
			return "GraphiteAquaSkin";
		}
	}

}
