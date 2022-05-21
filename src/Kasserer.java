import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kasserer {
  JFrame frameKasserer;
  JButton buttonVisMedlemmerIRestance;
  JButton buttonBudget;
  JButton buttonÆndreRestance;
  JButton buttonExit;
  //  JTextArea textAreaVisning;
  JPanel jPanelKnapper;
  JPanel jPanelStoreOmråde;
  JTextArea textAreavisMedlemmerPanel = new JTextArea();
  JScrollPane jScrollPanevisMembers;


  public Kasserer() {
    frameKasserer = new JFrame();
    frameKasserer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameKasserer.setVisible(true);
    frameKasserer.setSize(800, 600);
    frameKasserer.setLocationRelativeTo(null);
    frameKasserer.setLayout(null);
    buttonBudget = new JButton("Vis budget");
    buttonVisMedlemmerIRestance = new JButton("Vis medlemmer i restance");
    buttonÆndreRestance = new JButton("Ændre restance");
    buttonExit = new JButton("Gem og til hovedmenu");

    //panel med knapperne
    jPanelKnapper = new JPanel();
    jPanelKnapper.setLayout(null);
    jPanelKnapper.setBackground(Color.BLUE);
    jPanelKnapper.setSize(800, 210);
    frameKasserer.add(jPanelKnapper);
    jPanelKnapper.setBounds(0, 0, 210, 800);

    jPanelKnapper.add(buttonBudget);
    jPanelKnapper.add(buttonVisMedlemmerIRestance);
    jPanelKnapper.add(buttonÆndreRestance);
    jPanelKnapper.add(buttonExit);

    buttonBudget.setBounds(15, 20, 180, 60);
    buttonVisMedlemmerIRestance.setBounds(15, 100, 180, 60);
    buttonÆndreRestance.setBounds(15, 180, 180, 60);
    buttonExit.setBounds(15, 260, 180, 60);

    //Det store område
    jPanelStoreOmråde = new JPanel(null);
    jPanelStoreOmråde.setSize(590, 800);
    frameKasserer.add(jPanelStoreOmråde);
    jPanelStoreOmråde.setBounds(210, 5, 590, 800);


    //knappernes funktion
    buttonVisMedlemmerIRestance.addActionListener(alShowMembers);
    buttonExit.addActionListener(alExit);


  }

  ActionListener alShowMembers = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      Border blackline = BorderFactory.createLineBorder(Color.black);
      jPanelStoreOmråde.removeAll();
      jPanelStoreOmråde.setLayout(new BorderLayout());
      jScrollPanevisMembers = new JScrollPane(textAreavisMedlemmerPanel);
      jPanelStoreOmråde.add(jScrollPanevisMembers);
      jScrollPanevisMembers.setEnabled(false);
      jScrollPanevisMembers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      jScrollPanevisMembers.setSize(575, 560);


      //TODO Nu mangler der kun nogle members!
    }
  };

  ActionListener alExit = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      //TODO noget med at gemme oplysningerne!
      frameKasserer.dispose();
      new Login().run();
    }
  };

}
