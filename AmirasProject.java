import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Math;
class Shape
{
	int x1=0;
	int y1=0;
	int x2=0;
	int y2=0;
	int color_status=0;
	int shape_status=3;
	int option_status=0;
	boolean filled_solid_status=false;
	boolean drawn_flag=false;
	int eraser_starting_index=0;/*this flag tells me what is the index of the element in the arraylist
	in which i stored the first object rectangle during my erasing stroke(in the press)*/
	int eraser_ending_index=0;/*this flag tells me what index of the element in the arraylist in which i stored the last object rectangle
	during my eraser stroke(in the release)*/
	int Free_hand_starting_index=0;/*this flag tells me what is the index of the element in the arraylist
	in which i stored the first object line during my freehand stroke(from the press tell the release)*/
	int Free_hand_ending_index=0;/*this flag tells me what is the index of the element in the arraylist
	in which i stored the last object line during my freehand stroke(in the release)*/
	int startingX=0;
	int startingY=0;
}

class Line extends Shape
{

}
class Rectangle extends Shape
{
	
}
class Circle extends Shape
{
	
}
class FreeBrush extends Shape
{
	
}
public class AmirasProject extends Applet implements MouseListener,MouseMotionListener
{
	int temp_eraser_x_position=0;
	int temp_eraser_y_position=0;
	int temp_FreeHand_x_position=0;
	int temp_FreeHand_y_position=0;	
	int starting_ind=0;
	int ending_ind=0;
	int removed_shape_flag=0;
	int options_status=0;
	int color_s=2;
	int shape_s=0;
	boolean stroke_s=true;
	boolean filled_solid_s=false;
	int drag_flag=0;
	Button red;
	Button green;
	Button blue;
	Button rectangle;
	Button circle;
	Button line;
	Button freeBrush;
	Button eraser;
	Button clearAll;
	Button undo;
	Button dottedStroke;
	Button solidStroke;
	Checkbox solid;
			
	int i=0;
	ArrayList<Shape> arr=new ArrayList<Shape>();

	public void init()
	{
		red = new Button("Red");
		green = new Button("Green");
		blue= new Button("Blue");
		rectangle= new Button("Rectangle");
		circle= new Button("Cirlce");
		line= new Button("Line");
		freeBrush= new Button("Free Brush");
		eraser= new Button("Eraser");
		clearAll= new Button("Clear All");
		undo= new Button("Undo");
		solid= new Checkbox("Solid Shape");
		this.addMouseListener(this); 
		addMouseMotionListener(this);
		
	    solid.addItemListener(new MyCheckBoxListener());
		red.addActionListener(new MyRedButtonbListener());
		blue.addActionListener(new MyBlueButtonbListener());
		green.addActionListener(new MyGreenButtonbListener());
		rectangle.addActionListener(new MyRectangleButtonbListener());
		circle.addActionListener(new MyCircleButtonbListener());
		line.addActionListener(new MyLineButtonbListener());
		freeBrush.addActionListener(new MyFreeBrushButtonbListener());
		eraser.addActionListener(new MyEraserButtonbListener());
		clearAll.addActionListener(new MyClearAllButtonbListener());
		undo.addActionListener(new MyUndoButtonbListener());
		
		add(solid);
		add(red);
		add(blue);
		add(green);
		add(rectangle);
		add(circle);
		add(line);
		add(freeBrush);
		add(eraser);
		add(clearAll);
		add(undo);
	}
	public void mousePressed(MouseEvent e)
	{
		Shape obj;
		switch(shape_s)
		{
			case 1:
				obj=new Rectangle();
			break;
			case 2:
				obj=new Circle();
			break;
			case 3:
				obj=new Line();
			break;
			case 4:
				obj=new FreeBrush();	
			default:
				obj=new FreeBrush();
		}

		arr.add(i,obj);			

		if(arr.get(i).drawn_flag==false)/*if the shape is still not drawn yet, then set its color, 
		stroke, shape, and solid state to the chosen states;
		if it is already drawn, then don't change its states and show it as it was before*/
		{
			arr.get(i).color_status=color_s;	
			arr.get(i).shape_status=shape_s;
			arr.get(i).filled_solid_status=filled_solid_s;
		}
		switch(shape_s)
		{
			case 1://rectangle or an eraser to be used
				if(options_status==1)//eraser option is chosen
				{
					starting_ind=i;
					arr.get(i).eraser_starting_index=i;
					arr.get(i).color_status=4;
					arr.get(i).option_status=1;
					arr.get(i).shape_status=1;
					arr.get(i).x1=e.getX();
					arr.get(i).y1=e.getY();
					temp_eraser_x_position=e.getX();
					temp_eraser_y_position=e.getY();
					drag_flag=1;/*because i do not need to drag the eraser in order to register it in the array list; 
					so we can set the flag as 1 so that when the user presses and releases without dragging, the object does not get removed*/
					repaint();
				}
				else
				{
					arr.get(i).x1=e.getX();
					arr.get(i).y1=e.getY();
					arr.get(i).x2=e.getX();
					arr.get(i).y2=e.getY();
					arr.get(i).startingX=e.getX();
					arr.get(i).startingY=e.getY();
					arr.get(i).color_status=color_s;
					repaint();
					
				}
			break;
			case 2:
					arr.get(i).x1=e.getX();
					arr.get(i).y1=e.getY();
					arr.get(i).x2=e.getX();
					arr.get(i).y2=e.getY();
					arr.get(i).startingX=e.getX();
					arr.get(i).startingY=e.getY();
					
					arr.get(i).color_status=color_s;
					repaint();
			break;
			case 3:
				arr.get(i).x1=e.getX();
				arr.get(i).y1=e.getY();
				arr.get(i).x2=e.getX();
				arr.get(i).y2=e.getY();
				arr.get(i).shape_status=shape_s;
				arr.get(i).color_status=color_s;
				repaint();
			break;
			case 4:
				arr.get(i).x1=e.getX();
				arr.get(i).y1=e.getY();
				arr.get(i).x2=e.getX();
				arr.get(i).y2=e.getY();
				arr.get(i).color_status=color_s;
				temp_FreeHand_x_position=e.getX();
				temp_FreeHand_y_position=e.getY();
				arr.get(i).Free_hand_starting_index=i;
				starting_ind=i;
				repaint();
			break;
			default:
				arr.get(i).x1=e.getX();
				arr.get(i).y1=e.getY();
				arr.get(i).x2=e.getX();
				arr.get(i).y2=e.getY();
				arr.get(i).color_status=color_s;
				temp_FreeHand_x_position=e.getX();
				temp_FreeHand_y_position=e.getY();
				arr.get(i).Free_hand_starting_index=i;
				starting_ind=i;
				repaint();
		
		}
	}
	public void mouseDragged(MouseEvent e)
	{
		switch(shape_s)
		{
			case 1://rectangle or an eraser to be used
				if(options_status==1)//eraser option is chosen
				{
					while(java.lang.Math.abs(temp_eraser_x_position-e.getX())>=7||java.lang.Math.abs(temp_eraser_y_position-e.getY())>=7)/*As long as
					you drag the mouse using the eraser;
					a new rectangular object will be created everytime you move your mouse by 7 pixels intervals*/
					{
						i++;
						arr.add(i,new Rectangle());
						arr.get(i).color_status=4;
						arr.get(i).option_status=1;
						arr.get(i).eraser_starting_index=starting_ind;
						arr.get(i).x1=e.getX();
						arr.get(i).y1=e.getY();
						arr.get(i).x2=e.getX();
						arr.get(i).y2=e.getY();
						arr.get(i).Free_hand_starting_index=starting_ind;
						arr.get(i).shape_status=1;
						repaint();						
						temp_eraser_x_position=e.getX();
						temp_eraser_y_position=e.getY();
					}
				}
				else
				{
					if(e.getY()<arr.get(i).startingY)
					{
						arr.get(i).y1=e.getY();
						arr.get(i).y2=arr.get(i).startingY;
					}
					else
					{
						arr.get(i).y2=e.getY();
					}
					if(e.getX()<arr.get(i).startingX)
					{
						arr.get(i).x1=e.getX();
						arr.get(i).x2=arr.get(i).startingX;
					}
					else
					{
						arr.get(i).x2=e.getX();
					}
					arr.get(i).color_status=color_s;
					if(filled_solid_s==true)
					{
						arr.get(i).filled_solid_status=true;
					}
					drag_flag=1;
					repaint();
				}
			break;
			case 2:
					if(e.getY()<arr.get(i).startingY)
					{
						arr.get(i).y1=e.getY();
						arr.get(i).y2=arr.get(i).startingY;
					}
					else
					{
						arr.get(i).y2=e.getY();
					}
					if(e.getX()<arr.get(i).startingX)
					{
						arr.get(i).x1=e.getX();
						arr.get(i).x2=arr.get(i).startingX;
					}
					else
					{
						arr.get(i).x2=e.getX();
					}
					arr.get(i).color_status=color_s;
					if(filled_solid_s==true)
					{
						arr.get(i).filled_solid_status=true;
					}
					drag_flag=1;
					repaint();
			break;
			case 3:
				arr.get(i).x2=e.getX();
				arr.get(i).y2=e.getY();
				arr.get(i).shape_status=shape_s;
				drag_flag=1;
				repaint();
			break;
			case 4:
				drag_flag=1;
				while(java.lang.Math.abs(temp_FreeHand_x_position-e.getX())>=7||java.lang.Math.abs(temp_FreeHand_y_position-e.getY())>=7)/*As long as
					you drag the mouse using the freehand option;
					a new line object will be created everytime you move your mouse by 7 pixels intervals*/
					{
						i++;
						arr.add(i,new FreeBrush());
						arr.get(i).color_status=color_s;
						arr.get(i).x1=temp_FreeHand_x_position;
						arr.get(i).y1=temp_FreeHand_y_position;
						arr.get(i).x2=e.getX();
						arr.get(i).y2=e.getY();
						arr.get(i).shape_status=4;
						repaint();						
						temp_FreeHand_x_position=e.getX();
						temp_FreeHand_y_position=e.getY();
					}
			break;
			default:
				drag_flag=1;
				while(java.lang.Math.abs(temp_FreeHand_x_position-e.getX())>=7||java.lang.Math.abs(temp_FreeHand_y_position-e.getY())>=7)/*As long as
					you drag the mouse using the freehand option;
					a new line object will be created everytime you move your mouse by 7 pixels intervals*/
					{
						i++;
						arr.add(i,new FreeBrush());
						arr.get(i).color_status=color_s;
						arr.get(i).x1=temp_FreeHand_x_position;
						arr.get(i).y1=temp_FreeHand_y_position;
						arr.get(i).x2=e.getX();
						arr.get(i).y2=e.getY();
						arr.get(i).shape_status=4;
						repaint();						
						temp_FreeHand_x_position=e.getX();
						temp_FreeHand_y_position=e.getY();
					}
		
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		switch(shape_s)
		{
			case 1://rectangle or an eraser to be used
				if(options_status==1)//eraser option is chosen
				{
					arr.get(i).eraser_ending_index=i;
					arr.get(i).eraser_starting_index=starting_ind;
					arr.get(i).color_status=4;
					arr.get(i).option_status=1;
					arr.get(i).shape_status=1;
					arr.get(i).x1=e.getX();
					arr.get(i).y1=e.getY();
					arr.get(i).x2=e.getX();
					arr.get(i).y2=e.getY();
					repaint();
				}
				else
				{
					if(e.getY()<arr.get(i).startingY)
					{
						arr.get(i).y1=e.getY();
						arr.get(i).y2=arr.get(i).startingY;
					}
					else
					{
						arr.get(i).y2=e.getY();
					}
					if(e.getX()<arr.get(i).startingX)
					{
						arr.get(i).x1=e.getX();
						arr.get(i).x2=arr.get(i).startingX;
					}
					else
					{
						arr.get(i).x2=e.getX();
					}
					arr.get(i).color_status=color_s;
					if(filled_solid_s==true)
					{
						arr.get(i).filled_solid_status=true;
					}
					
					repaint();
				}
			break;
			case 2:
					if(e.getY()<arr.get(i).startingY)
					{
						arr.get(i).y1=e.getY();
						arr.get(i).y2=arr.get(i).startingY;
					}
					else
					{
						arr.get(i).y2=e.getY();
					}
					if(e.getX()<arr.get(i).startingX)
					{
						arr.get(i).x1=e.getX();
						arr.get(i).x2=arr.get(i).startingX;
					}
					else
					{
						arr.get(i).x2=e.getX();
					}
					arr.get(i).color_status=color_s;
					if(filled_solid_s==true)
					{
						arr.get(i).filled_solid_status=true;
					}
					repaint();
			break;
			case 3:
				arr.get(i).x2=e.getX();
				arr.get(i).y2=e.getY();
				repaint();
			break;
			case 4:
				arr.get(i).Free_hand_starting_index=starting_ind;
				arr.get(i).Free_hand_ending_index=i;
			break;
			default:
				arr.get(i).Free_hand_starting_index=starting_ind;
				arr.get(i).Free_hand_ending_index=i;
			
		}
		arr.get(i).drawn_flag=true;
		if(drag_flag==0)
		{
			arr.remove(i);	
			i=arr.size();
		}
		else
		{
			i++;
		}
		
		drag_flag=0;//initialize the drag flag again/
		
	}
	public void mouseClicked(MouseEvent e)
	{}
	public void mouseEntered(MouseEvent e) 
	{}
	public void mouseExited(MouseEvent e) 
	{}
	public void mouseMoved(MouseEvent e) 
	{}

	class MyCheckBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(filled_solid_s==true)
			{
				filled_solid_s=false;
			}
			else
			{
				filled_solid_s=true;
			}
			repaint();
		}
	}
	class MyRedButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			color_s=1;//1 refers to red color chosen//
			repaint();
		}
	}
	class  MyBlueButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			color_s=2;//2 refers to blue color chosen//
			repaint();
		}
	}
	class MyGreenButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			color_s=3;//3 refers to green color chosen//
			repaint();
		}
	}
	class MyRectangleButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			shape_s=1;//1 refers to rectangle shape chosen//
			//color_s=2;
			repaint();
		}
	}
	class MyCircleButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			shape_s=2;//2 refers to circle shape chosen//
			repaint();
		}
	}
	class MyLineButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			shape_s=3;//3 refers to Line shape chosen//
			options_status=0;
			repaint();
		}
	}
	class MyFreeBrushButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			shape_s=4;//4 refers to Free Brush shape chosen
			repaint();
		}
	}
	class MyEraserButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			options_status=1;// 1 refers to eraser option chosen
			shape_s=1;//rectangular eraser
			repaint();
		}
	}
	class MyClearAllButtonbListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			arr.clear();
			i=0;//reinitialize the index when we clear our array
			repaint();
			options_status=0;//set the options status to default zero value which refers to unchosen option//
		}
	}
	class MyUndoButtonbListener implements ActionListener
	{

		public void actionPerformed(ActionEvent ev)
		{
			int	removed_shape_flag=0;
			if(arr.size()>0)
			{
				if(arr.get(i-1).option_status==1 && removed_shape_flag==0)// 1 refers to eraser option chosen//
				{
					starting_ind=arr.get(i-1).eraser_starting_index;
					ending_ind=arr.get(i-1).eraser_ending_index;
					
					for(int k=ending_ind;k>=starting_ind;k--)
					{
						arr.remove(k);
						i--;
					}
					removed_shape_flag=1;
				}
				if(arr.get(i-1).shape_status==4 && removed_shape_flag==0)// 4 refers to freehand chosen and we want the undo to remove the last drawn freehand only
				{
					starting_ind=arr.get(i-1).Free_hand_starting_index;
					ending_ind=arr.get(i-1).Free_hand_ending_index;
					
					for(int k=ending_ind;k>=starting_ind;k--)
					{
						arr.remove(k);
						i--;
					}
					removed_shape_flag=1;
				}
				if(arr.size()!=0 && removed_shape_flag==0)
				{
					arr.remove(i-1);
					if(arr.size()!=0)
					{i--;}
					
				}
			}
			else//set the conditions to default conditions when array size is zero
			{
				i=0;
				options_status=0;
			}

			repaint();
		}
	}
	
	public void paint(Graphics g)
	{
		for(int k=0;k<=(arr.size()-1);k++)
		{
			switch(arr.get(k).color_status)
			{
				case 1:
					g.setColor(Color.red);
				break;
				case 2:
					g.setColor(Color.blue);
				break;
				case 3:
					g.setColor(Color.green);
				break;
				case 4:
					g.setColor(Color.white);
			}
			switch(arr.get(k).shape_status)
			{
				case 1:
					if(arr.get(k).option_status==1)//eraser option is chosen
					{
						g.drawRect((arr.get(k).x1)-5,(arr.get(k).y1)-5,20,20);
						g.fillRect((arr.get(k).x1)-5,(arr.get(k).y1)-5,20,20);
					}
					else
					{
						g.drawRect(arr.get(k).x1,arr.get(k).y1,java.lang.Math.abs(arr.get(k).x1-arr.get(k).x2),java.lang.Math.abs(arr.get(k).y1-arr.get(k).y2));
						if(arr.get(k).filled_solid_status==true)
						{
							g.fillRect(arr.get(k).x1,arr.get(k).y1,java.lang.Math.abs(arr.get(k).x1-arr.get(k).x2),java.lang.Math.abs(arr.get(k).y1-arr.get(k).y2));
							
						}
					}
				break;
				case 2:
					
					g.drawOval(arr.get(k).x1,(arr.get(k).y1),java.lang.Math.abs(arr.get(k).x1-arr.get(k).x2),java.lang.Math.abs(arr.get(k).y1-arr.get(k).y2));
					if(arr.get(k).filled_solid_status==true)
						{
							g.fillOval(arr.get(k).x1,arr.get(k).y1,java.lang.Math.abs(arr.get(k).x1-arr.get(k).x2),java.lang.Math.abs(arr.get(k).y1-arr.get(k).y2));
							
						}
				break;
				case 3:
					g.drawLine(arr.get(k).x1,arr.get(k).y1,arr.get(k).x2,arr.get(k).y2);
					//g.drawString("The index is: "+k, arr.get(k).x1, arr.get(k).y1);	
				break;
				case 4:
					g.drawLine(arr.get(k).x1,arr.get(k).y1,arr.get(k).x2,arr.get(k).y2);
				break;
				default:
					
			}	
		}
		g.setColor(Color.blue);
		g.drawString("The size of the dynamic array is: "+arr.size(), 90, 90);

	}
}

