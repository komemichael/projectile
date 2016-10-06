import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


@SuppressWarnings({ "serial", "unused" })
public class Projectile extends ApplicationFrame
{

public Projectile(String  title) {
		super(title);
	}

public static void main(String[] args)
  {
    //create new scanner input object.
    Scanner sc = new Scanner(System.in);
    
    
    /********************************************************
     * Defines
     *    initial height of the object.
     *    time the object takes to travel, initially set to 0.
     *    the initial distance always set to 0.
     * 
     * what I need: Velocity, Angle and Height.
     *******************************************************/
    
    //************************************************************System.out.println("Enter the velocity height of release of projectile: ");
    
    //gets the input from the user to specify the height from travel
    System.out.println("Enter the height you want to release the projectile in metres: ");
    double initialHeight = sc.nextDouble();
    
    //Gets the user to enter the velocity of projectile.
    System.out.println("Enter the velocity of projectile in metres per seconds: ");
    double velocity = sc.nextDouble();

    //Gets the user to enter the Angle of projectile.
    System.out.println("Enter the angle of the projected object in degrees: ");
    int angledeg = sc.nextInt();
    double angle = (angledeg * Math.PI) / 180;
    
    Position[] posn = solve(initialHeight,velocity,angle);
    
    
//    final XYSeriesDemo demo = new XYSeriesDemo("XY Series Demo");//, posn);
//    demo.pack();
//    RefineryUtilities.centerFrameOnScreen(demo);
//    demo.setVisible(true);
    
//    XYLineChart_AWT chart = new XYLineChart_AWT("Browser Usage Statistics", "Which Browser are you using?");
//    chart.pack( );          
//    RefineryUtilities.centerFrameOnScreen( chart );          
//    chart.setVisible( true );
    
    sc.close();
  }
  
  public static Position[] solve(double y0, double v, double theta)
  {
    int time = 0;
    
    double maxHeight = 0;
    
    if (y0 == 0)
    {
      maxHeight = maxHeight(v,theta);
    }
    else
    {
      maxHeight = maxHeight(v,theta) + y0;
    }
    
    double horzRange = horzRange(v,theta);
    double timeofFlight = time(v,theta);
      
    //defines the position of the object
    Position[] position = new Position[(int) horzRange];
    
    if(theta != 0 && y0 == 0)
    {
      System.out.println("the max height is: " + maxHeight);
      System.out.println("the max rangee is: " + horzRange);
      
      for( time = 0; time <= (int)timeofFlight + 1; time++)
      {
        position[time] = new Position(calcXPosn(v,time,theta), (calcYPosn(v,time, theta)));
        System.out.println(position[time].toString());
      } 
    }
    else if(theta != 0 && y0 != 0)
    {
      System.out.println("the max height is: " + maxHeight);
      System.out.println("the max rangee is: " + horzRange);
      
      for( time = 0; time <= (int)timeofFlight + 1; time++)
      {
        position[time] = new Position(calcXPosn(v,time,theta), y0 + (calcYPosn(v,time, theta)));
        System.out.println(position[time].toString());
      } 
    }
    else
    {
      System.out.println("The Object travelled " + angle0(y0,v) + " metres");
      timeofFlight = angle0(y0,v) / v;
    }
    
    System.out.println("The total time of flight is: " + timeofFlight+ " seconds");
    
    return position;
  }
  
  public static double angle0(double y0, double v)
  {
    //0.25 is defined as kome's frictional coefficient.
    double distance = 0;
    if (y0 == 0)
    {
      distance = (0.5*v*v)/(9.8 * 0.25);
    }
    else
    {
      distance = (v*v) / 9.8;
    }
    
    return distance;
  }
  
  public static double time(double v0, double theta)
  {
    return (2*v0* Math.sin(theta)) / 9.8;
  }
  
  public static double calcXPosn(double v0, double t, double theta)
  {
    return v0 * t * Math.cos(theta);
  }
  
  public static double calcYPosn(double v0, double t, double theta)
  {
    return (v0 * t * Math.sin(theta)) - 0.5*(9.8*t*t);
  }
  
  public static double horzRange(double v, double theta)
  {
    return (v*v * Math.sin(2*theta)) / 9.8;
  }
  
  public static double maxHeight(double v, double theta)
  {
    return (v * v * Math.sin(theta) * Math.sin(theta)) / (2 * 9.8);
  }
  
}

class Position
{
  double x;
  double y;
  
  public Position()
  {
    this.x = 0;
    this.y = 0;
  }
  
  public Position(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  public double getX()
  {
    return  this.x;
  }
  
  public void setX(double x)
  {
    this.x  = x;
  }
  
   public void setY(double y)
  {
    this.y  = y;
  }
  
  public double getY()
  {
    return  this.y;
  }
  
  public String toString()
  {
    return x + "," + y;
  }
}

//@SuppressWarnings("serial")
//class XYSeriesDemo extends ApplicationFrame 
//{
///**
// * A demonstration application showing an XY series containing a null value.
// *
// * @param title  the frame title.
// */
//	public XYSeriesDemo(final String title){// , Position[] posn) {
//
//	    super(title);
//	    final XYSeries series = new XYSeries("Random Data");
//	    
//	    series.add(1.0, 500.2);
//	    series.add(5.0, 694.1);
//	    
////	    for(int i = 0; i < posn.length; i++)
////	    {
////	    	System.out.println(posn[i].toString());
////	    	//series.add(posn[i].getX(), posn[i].getY());
////	    }
//	   
//	    final XYSeriesCollection data = new XYSeriesCollection(series);
//	    final JFreeChart chart = ChartFactory.createXYLineChart(
//	    	"XY Series Demo",
//	        "X", 
//	        "Y", 
//	        data,
//	        PlotOrientation.VERTICAL,
//	        true,
//	        true,
//	        false
//	    );
//	
//	    final ChartPanel chartPanel = new ChartPanel(chart);
//	    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//	    setContentPane(chartPanel);
//	}
//}
//
//@SuppressWarnings("serial")
//class XYLineChart_AWT extends ApplicationFrame 
//{
//   public XYLineChart_AWT( String applicationTitle, String chartTitle )
//   {
//      super(applicationTitle);
//      JFreeChart xylineChart = ChartFactory.createXYLineChart(
//         chartTitle ,
//         "Category" ,
//         "Score" ,
//         createDataset() ,
//         PlotOrientation.VERTICAL ,
//         true , true , false);
//         
//      ChartPanel chartPanel = new ChartPanel( xylineChart );
//      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//      final XYPlot plot = xylineChart.getXYPlot( );
//      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
//      renderer.setSeriesPaint( 0 , Color.RED );
//      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
//      plot.setRenderer( renderer ); 
//      setContentPane( chartPanel ); 
//   }
//   
//   privat e XYDataset createDataset( )
//   {
//      final XYSeries firefox = new XYSeries( "Firefox" );          
//      firefox.add( 1.0 , 1.0 );          
//      firefox.add( 2.0 , 4.0 );          
//      firefox.add( 3.0 , 3.0 );    
//      
//      final XYSeriesCollection dataset = new XYSeriesCollection( );          
//      dataset.addSeries( firefox );  
//      return dataset;
//   }
//}

