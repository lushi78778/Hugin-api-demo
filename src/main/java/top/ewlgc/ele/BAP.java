package top.ewlgc.ele;

import COM.hugin.HAPI.*;

import java.util.ListIterator;
import java.awt.geom.Point2D;

/**
 * @ClassName BAP
 * @Description This example describes how a belief network can be constructed using the Hugin Java API.
 * The network consists of three numbered nodes. Two of the nodes take on values 0, 1, and 2.
 * The third node is the sum of the two other nodes. Once the Bayesian network is constructed,
 * the network is saved as a NET specification file, and an initial propagation is performed.
 * Finally, the marginals of the nodes are printed on standard output.
 * @Author lushi
 * @Date 2022/6/3 19:21
 */
public class BAP {

    protected Domain domain;

    /**
     * Build a Bayesian network and propagate evidence.
     */
    public BAP() {
        try {
            domain = new Domain();
            buildNetwork();
            domain.saveAsNet("src/main/resources/builddomain.net");
            domain.compile();
            propagateEvidenceInNetwork();
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Propagate evidence in domain.
     */
    protected void propagateEvidenceInNetwork() {
        try {
            domain.propagate(Domain.H_EQUILIBRIUM_SUM,
                    Domain.H_EVIDENCE_MODE_NORMAL);
            printNodeMarginals(domain);
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * print node marginals.
     */
    protected void printNodeMarginals(Domain d) {
        try {
            ListIterator it = domain.getNodes().listIterator();
            while (it.hasNext()) {
                DiscreteChanceNode node = (DiscreteChanceNode) it.next();
                System.out.println(node.getLabel());
                for (int i = 0, n = (int) node.getNumberOfStates(); i < n; i++)
                    System.out.println("-" + node.getStateLabel(i)
                            + " " + node.getBelief(i));
            }
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructs numbered discrete chance node.
     */
    protected NumberedDCNode constructNDC(String label, String name, int n) {
        try {
            NumberedDCNode node = new NumberedDCNode(domain);
            node.setNumberOfStates(n);
            for (int i = 0; i < n; i++)
                node.setStateValue(i, i);
            for (int i = 0; i < n; i++)
                node.setStateLabel(i, (new Integer(i)).toString());
            node.setLabel(label);
            node.setName(name);
            return node;
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Build the structure.
     */
    protected void buildStructure
    (NumberedDCNode A, NumberedDCNode B, NumberedDCNode C) {
        try {
            C.addParent(A);
            C.addParent(B);
            A.setPosition(new Point2D.Double(100, 200));
            B.setPosition(new Point2D.Double(200, 200));
            C.setPosition(new Point2D.Double(150, 50));
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Expression for C
     */
    protected void buildExpressionForC
    (NumberedDCNode A, NumberedDCNode B, NumberedDCNode C) {
        try {
            NodeList modelNodes = new NodeList();
            Model model = new Model(C, modelNodes);
            NodeExpression exprA = new NodeExpression(A);
            NodeExpression exprB = new NodeExpression(B);
            AddExpression exprC = new AddExpression(exprA, exprB);
            model.setExpression(0, exprC);
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Specify the prior distribution of A and B.
     */
    protected void specifyDistributions(NumberedDCNode A, NumberedDCNode B) {
        try {
            Table table;
            table = A.getTable();
            double[] data = new double[3];
            data[0] = 0.1;
            data[1] = 0.2;
            data[2] = 0.7;
            table.setData(data);
            table = B.getTable();
            table.setDataItem(0, 0.2);
            table.setDataItem(1, 0.2);
            table.setDataItem(2, 0.6);
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Build the Bayesian network.
     */
    protected void buildNetwork() {
        try {
            domain.setNodeSize(new Point2D.Double(50, 30));
            NumberedDCNode A = constructNDC("A", "A", 3);
            NumberedDCNode B = constructNDC("B", "B", 3);
            NumberedDCNode C = constructNDC("C", "C", 5);
            buildStructure(A, B, C);
            buildExpressionForC(A, B, C);
            specifyDistributions(A, B);
        } catch (ExceptionHugin e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * Build a Bayesian network and perform a propagation of evidence.
 * Print the results.
 */
class BuildAndPropagate {
    static public void main(String args[]) {
        new BAP();
    }
}