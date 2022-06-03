package top.ewlgc.ele;

import COM.hugin.HAPI.*;

/**
 * @ClassName BayesDemo
 * @Description The following code demonstrates that loading a network domain,
 * creating a new network is easier, just call Domian's no-parameter construction.
 * @Author lushi
 * @Date 2022/6/3 18:19
 */
public class BayesDemo {
    public static void main(String[] args) throws ExceptionHugin {

        Domain domain = new Domain("src/main/resources/fire.net", new DefaultClassParseListener());

        domain.openLogFile("src/main/resources/fire.net.log");

        domain.compile();

        domain.closeLogFile();

        domain.propagate(Domain.H_EQUILIBRIUM_SUM, Domain.H_EVIDENCE_MODE_NORMAL);

        DiscreteChanceNode node = (DiscreteChanceNode) domain.getNodeByName("Fire");

        node.selectState(1);

        PrintNodeMarginals(domain);

    }

    public static void PrintNodeMarginals(Domain domain) throws ExceptionHugin {
        NodeList nlist = domain.getNodes();
        for (int i = 0; i < nlist.size(); i++) {
            DiscreteChanceNode node = (DiscreteChanceNode) nlist.get(i);
            int nStates = (int) node.getNumberOfStates();
            System.out.println(node.getLabel());
            for (int j = 0; j < nStates; j++) {
                System.out.println(("-" + node.getStateLabel(j) + " " + node.getBelief(j)));
            }

        }

    }
}
