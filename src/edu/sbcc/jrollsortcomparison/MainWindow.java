package edu.sbcc.jrollsortcomparison;

import java.io.*;
import java.util.*;

import com.cloudgarden.resource.*;

import edu.sbcc.jrollsortcomparison.SortHelper.*;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation, company or business for any purpose whatever) then
 * you should purchase a license for each developer using Jigloo. Please visit www.cloudgarden.com for details. Use of
 * Jigloo implies acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO
 * JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainWindow extends org.eclipse.swt.widgets.Composite {

	Properties appSettings = new Properties();
	Cursor defaultCursor; // To change the cursor to an arrow at any point after MainWindow() has executed, use
							// setCursor(defaultCursor);
	Cursor waitCursor; // To change the cursor to an hourglass at any point after MainWindow() has executed, use
						// setCursor(waitCursor);
	private Menu menu1;
	private MenuItem aboutMenuItem;
	private Menu helpMenu;
	private MenuItem helpMenuItem;
	private MenuItem exitMenuItem;
	private MenuItem closeFileMenuItem;
	private MenuItem saveFileMenuItem;
	private MenuItem newFileMenuItem;
	private MenuItem openFileMenuItem;
	private ToolItem newToolItem;
	private ToolItem saveToolItem;
	private ToolItem openToolItem;
	private ToolBar toolBar;
	@SuppressWarnings("unused")
	private MenuItem fileMenuSep2;
	@SuppressWarnings("unused")
	private MenuItem fileMenuSep1;
	private Composite clientArea;
	private Label statusText;
	private Composite statusArea;
	private Button quickSortButton;
	private Button insertionButton;
	private Label maxLabel;
	private Label stepLabel;
	private Label minLabel;
	private Text minText;
	private Text stepText;
	private Button sortButton;
	private Button heapSortButton;
	private Button mergeSortButton;
	private Label resultsLabel;
	private Text resultsText;
	private Menu fileMenu;
	private MenuItem fileMenuItem;
	SortHelper sortHelper;
	private Text maxText;
	
	SortType selectedSort;

	{
		// Register as a resource user - SWTResourceManager will handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		@SuppressWarnings("unused")
		MainWindow inst = new MainWindow(shell, SWT.NULL);
		shell.setLayout(new FillLayout());
		shell.setImage(SWTResourceManager.getImage("images/16x16.png"));
		shell.setText("Change This Title");
		shell.setBackgroundImage(SWTResourceManager.getImage("images/ToolbarBackground.gif"));
		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public MainWindow(Composite parent, int style) {
		super(parent, style);
		initGUI();
		setPreferences();
		waitCursor = getDisplay().getSystemCursor(SWT.CURSOR_WAIT);
		defaultCursor = getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		clientArea.setFocus();
		sortHelper = new SortHelper();
	}

	// Load application settings from .ini file
	private void setPreferences() {
		try {
			appSettings.load(new FileInputStream("appsettings.ini"));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		// By default, center window
		int width = Integer.parseInt(appSettings.getProperty("width", "640"));
		int height = Integer.parseInt(appSettings.getProperty("height", "480"));
		Rectangle screenBounds = getDisplay().getBounds();
		int defaultTop = (screenBounds.height - height) / 2;
		int defaultLeft = (screenBounds.width - width) / 2;
		int top = Integer.parseInt(appSettings.getProperty("top", String.valueOf(defaultTop)));
		int left = Integer.parseInt(appSettings.getProperty("left", String.valueOf(defaultLeft)));
		getShell().setSize(width, height);
		getShell().setLocation(left, top);
		saveShellBounds();
	}

	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		try {
			getShell().addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent evt) {
					shellWidgetDisposed(evt);
				}
			});

			getShell().addControlListener(new ControlAdapter() {
				public void controlResized(ControlEvent evt) {
					shellControlResized(evt);
				}
			});

			getShell().addControlListener(new ControlAdapter() {
				public void controlMoved(ControlEvent evt) {
					shellControlMoved(evt);
				}
			});

			GridLayout thisLayout = new GridLayout();
			this.setLayout(thisLayout);
			{
				GridData toolBarLData = new GridData();
				toolBarLData.grabExcessHorizontalSpace = true;
				toolBarLData.horizontalAlignment = GridData.FILL;
				toolBar = new ToolBar(this, SWT.FLAT);
				toolBar.setLayoutData(toolBarLData);
				toolBar.setBackgroundImage(SWTResourceManager.getImage("images/ToolbarBackground.gif"));
				{
					newToolItem = new ToolItem(toolBar, SWT.NONE);
					newToolItem.setImage(SWTResourceManager.getImage("images/new.gif"));
					newToolItem.setToolTipText("New");
				}
				{
					openToolItem = new ToolItem(toolBar, SWT.NONE);
					openToolItem.setToolTipText("Open");
					openToolItem.setImage(SWTResourceManager.getImage("images/open.gif"));
					openToolItem.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							openToolItemWidgetSelected(evt);
						}
					});
				}
				{
					saveToolItem = new ToolItem(toolBar, SWT.NONE);
					saveToolItem.setToolTipText("Save");
					saveToolItem.setImage(SWTResourceManager.getImage("images/save.gif"));
				}
			}
			{
				clientArea = new Composite(this, SWT.NONE);
				GridData clientAreaLData = new GridData();
				clientAreaLData.grabExcessHorizontalSpace = true;
				clientAreaLData.grabExcessVerticalSpace = true;
				clientAreaLData.horizontalAlignment = GridData.FILL;
				clientAreaLData.verticalAlignment = GridData.FILL;
				clientArea.setLayoutData(clientAreaLData);
				clientArea.setLayout(null);
				{
					resultsText = new Text(clientArea, SWT.MULTI | SWT.WRAP);
					resultsText.setText("");
					resultsText.setBounds(12, 149, 450, 108);
				}
				{
					sortButton = new Button(clientArea, SWT.PUSH | SWT.CENTER);
					sortButton.setText("Sort Data");
					sortButton.setBounds(198, 92, 60, 30);
					sortButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							sortButtonWidgetSelected(evt);
						}
					});
				}
				{
					stepText = new Text(clientArea, SWT.NONE);
					stepText.setBounds(198, 43, 60, 16);
					stepText.setText("10");
				}
				{
					minText = new Text(clientArea, SWT.NONE);
					minText.setBounds(198, 21, 60, 16);
					minText.setText("10");
				}
				{
					minLabel = new Label(clientArea, SWT.NONE);
					minLabel.setText("Min #");
					minLabel.setBounds(138, 21, 54, 16);
				}
				{
					stepLabel = new Label(clientArea, SWT.NONE);
					stepLabel.setText("Step");
					stepLabel.setBounds(138, 43, 54, 16);
				}
				{
					maxLabel = new Label(clientArea, SWT.NONE);
					maxLabel.setText("Max #");
					maxLabel.setBounds(138, 65, 54, 15);
				}
				{
					insertionButton = new Button(clientArea, SWT.RADIO | SWT.LEFT);
					insertionButton.setText("Insertion Sort");
					insertionButton.setBounds(12, 11, 103, 30);
					insertionButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							insertionButtonWidgetSelected(evt);
						}
					});
				}
				{
					quickSortButton = new Button(clientArea, SWT.RADIO | SWT.LEFT);
					quickSortButton.setText("Quick Sort");
					quickSortButton.setBounds(12, 32, 103, 30);
					quickSortButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							quickSortButtonWidgetSelected(evt);
						}
					});
				}
				{
					resultsLabel = new Label(clientArea, SWT.NONE);
					resultsLabel.setText("Results");
					resultsLabel.setSize(60, 30);
					resultsLabel.setBounds(12, 117, 60, 30);
				}
				{
					mergeSortButton = new Button(clientArea, SWT.RADIO | SWT.LEFT);
					mergeSortButton.setText("Merge Sort");
					mergeSortButton.setBounds(12, 57, 81, 30);
					mergeSortButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							mergeSortButtonWidgetSelected(evt);
						}
					});
				}
				{
					heapSortButton = new Button(clientArea, SWT.RADIO | SWT.LEFT);
					heapSortButton.setText("Heap Sort");
					heapSortButton.setBounds(12, 81, 81, 30);
					heapSortButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							heapSortButtonWidgetSelected(evt);
						}
					});
				}
				{
					maxText = new Text(clientArea, SWT.NONE);
					maxText.setText("10");
					maxText.setBounds(198, 65, 60, 15);
				}
			}
			{
				statusArea = new Composite(this, SWT.NONE);
				GridLayout statusAreaLayout = new GridLayout();
				statusAreaLayout.makeColumnsEqualWidth = true;
				statusAreaLayout.horizontalSpacing = 0;
				statusAreaLayout.marginHeight = 0;
				statusAreaLayout.marginWidth = 0;
				statusAreaLayout.verticalSpacing = 0;
				statusAreaLayout.marginLeft = 3;
				statusAreaLayout.marginRight = 3;
				statusAreaLayout.marginTop = 3;
				statusAreaLayout.marginBottom = 3;
				statusArea.setLayout(statusAreaLayout);
				GridData statusAreaLData = new GridData();
				statusAreaLData.horizontalAlignment = GridData.FILL;
				statusAreaLData.grabExcessHorizontalSpace = true;
				statusArea.setLayoutData(statusAreaLData);
				statusArea.setBackground(SWTResourceManager.getColor(239, 237, 224));
				{
					statusText = new Label(statusArea, SWT.BORDER);
					statusText.setText(" Ready");
					GridData txtStatusLData = new GridData();
					txtStatusLData.horizontalAlignment = GridData.FILL;
					txtStatusLData.grabExcessHorizontalSpace = true;
					txtStatusLData.verticalIndent = 3;
					statusText.setLayoutData(txtStatusLData);
				}
			}
			thisLayout.verticalSpacing = 0;
			thisLayout.marginWidth = 0;
			thisLayout.marginHeight = 0;
			thisLayout.horizontalSpacing = 0;
			thisLayout.marginTop = 3;
			this.setSize(474, 312);
			{
				menu1 = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(menu1);
				{
					fileMenuItem = new MenuItem(menu1, SWT.CASCADE);
					fileMenuItem.setText("&File");
					{
						fileMenu = new Menu(fileMenuItem);
						{
							newFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							newFileMenuItem.setText("&New");
							newFileMenuItem.setImage(SWTResourceManager.getImage("images/new.gif"));
						}
						{
							openFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							openFileMenuItem.setText("&Open");
							openFileMenuItem.setImage(SWTResourceManager.getImage("images/open.gif"));
							openFileMenuItem.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(SelectionEvent evt) {
									openFileMenuItemWidgetSelected(evt);
								}
							});
						}
						{
							closeFileMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							closeFileMenuItem.setText("Close");
						}
						{
							fileMenuSep1 = new MenuItem(fileMenu, SWT.SEPARATOR);
						}
						{
							saveFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							saveFileMenuItem.setText("&Save");
							saveFileMenuItem.setImage(SWTResourceManager.getImage("images/save.gif"));
						}
						{
							fileMenuSep2 = new MenuItem(fileMenu, SWT.SEPARATOR);
						}
						{
							exitMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							exitMenuItem.setText("E&xit");
							exitMenuItem.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(SelectionEvent evt) {
									exitMenuItemWidgetSelected(evt);
								}
							});
						}
						fileMenuItem.setMenu(fileMenu);
					}
				}
				{
					helpMenuItem = new MenuItem(menu1, SWT.CASCADE);
					helpMenuItem.setText("&Help");
					{
						helpMenu = new Menu(helpMenuItem);
						{
							aboutMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
							aboutMenuItem.setText("&About");
							aboutMenuItem.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(SelectionEvent evt) {
									aboutMenuItemWidgetSelected(evt);
								}
							});
						}
						helpMenuItem.setMenu(helpMenu);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exitMenuItemWidgetSelected(SelectionEvent evt) {
		try {
			// Save app settings to file
			appSettings.store(new FileOutputStream("appsettings.ini"), "");
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		getShell().dispose();
	}

	private void openFileMenuItemWidgetSelected(SelectionEvent evt) {
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
		String filename = dialog.open();
		if (filename != null) {
			getShell().setText(filename);
		}
	}

	private void openToolItemWidgetSelected(SelectionEvent evt) {
		openFileMenuItemWidgetSelected(evt);
	}

	private void aboutMenuItemWidgetSelected(SelectionEvent evt) {
		MessageBox message = new MessageBox(getShell(), SWT.OK | SWT.ICON_INFORMATION);
		message.setText("About Change_This_Title");
		message.setMessage("Change this about message\n\nApplicationName v1.0");
		message.open();
	}

	private void shellWidgetDisposed(DisposeEvent evt) {
		try {
			// Save app settings to file
			appSettings.store(new FileOutputStream("appsettings.ini"), "");
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	private void shellControlResized(ControlEvent evt) {
		saveShellBounds();
	}

	// Save window location in appSettings hash table
	private void saveShellBounds() {
		// Save window bounds in app settings
		Rectangle bounds = getShell().getBounds();
		appSettings.setProperty("top", String.valueOf(bounds.y));
		appSettings.setProperty("left", String.valueOf(bounds.x));
		appSettings.setProperty("width", String.valueOf(bounds.width));
		appSettings.setProperty("height", String.valueOf(bounds.height));
	}

	private void shellControlMoved(ControlEvent evt) {
		saveShellBounds();
	}

	@SuppressWarnings("unused")
	private void setStatus(String message) {
		statusText.setText(message);
	}

	private void sortData() {
		// add logic for the insert type
		// Move this to the SortHelper Method to make it less coupled
		int minVal = Integer.parseInt(minText.getText());
		int stepVal = Integer.parseInt(this.stepText.getText());
		int maxVal = Integer.parseInt(this.maxText.getText());
				
		resultsText.setText("");
		for (int i = minVal; i <= maxVal; i += stepVal) {
			SortResult result = sortHelper.timedSort(i, this.selectedSort);
			resultsText.setText(resultsText.getText() + result.getN() + "\t" + result.getDuration() + "\r\n");
		}
	}

	private void sortButtonWidgetSelected(SelectionEvent evt) {
		// add logic in this method for checking which button is filled in
		sortData();
		// TODO add your code for sortButton.widgetSelected
	}
	
	private void insertionButtonWidgetSelected(SelectionEvent evt) {
		System.out.println("insertionButton.widgetSelected, event="+evt);
		this.selectedSort = selectedSort.INSERTION;
	}
	
	private void quickSortButtonWidgetSelected(SelectionEvent evt) {
		System.out.println("quickSortButton.widgetSelected, event="+evt);
		this.selectedSort = selectedSort.QUICKSORT;
	}
	
	private void mergeSortButtonWidgetSelected(SelectionEvent evt) {
		System.out.println("mergeSortButton.widgetSelected, event="+evt);
		this.selectedSort = selectedSort.MERGESORT;
	}
	
	private void heapSortButtonWidgetSelected(SelectionEvent evt) {
		System.out.println("heapSortButton.widgetSelected, event="+evt);
		this.selectedSort = selectedSort.HEAPSORT;
	}
}
