public class RBTree {

    private RBTNode mRoot; // 根结点

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        mRoot = null;
    }

    /**
     * 获取父节点 若没有父节点，则返回null
     * 
     * @param node
     */
    private boolean colorOf(RBTNode node) {
        return node != null ? node.color : BLACK;
    }

    /**
     * 是否为红色节点
     * 
     * @param node
     * @return true为红色节点，false为黑色节点
     */
    private boolean isRed(RBTNode node) {
        return node != null && colorOf(node) == RED;
    }

    /**
     * 是否为黑色节点
     * 
     * @param node
     * @return true为红色节点，false为黑色节点
     */
    private boolean isBlack(RBTNode node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 将节点设置为黑色节点
     * 
     * @param node
     */
    private void setBlack(RBTNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 将节点设置为红色节点
     * 
     * @param node
     */
    private void setRed(RBTNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 获取父节点 若没有父节点，则返回null
     * 
     * @param node
     */
    private RBTNode parentOf(RBTNode node) {
        return node != null ? node.parent : null;
    }

    /**
     * 设置节点的父节点
     * 
     * @param node
     * @param parent
     */
    private void setParent(RBTNode node, RBTNode parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    /**
     * 设置节点的颜色
     * 
     * @param node
     * @param color
     */
    private void setColor(RBTNode node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    private void preOrder(RBTNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(mRoot);
    }

    private void inOrder(RBTNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(mRoot);
    }

    private void postOrder(RBTNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(mRoot);
    }

    /**
     * 查找红黑树中或键值最接近key的节点
     * 
     * @param node 开始查找的位置
     * @param key  要查找的值
     * @return 最接近key的节点
     */
    private RBTNode search(RBTNode node, int key) {
        if (node == null) {
            return node;
        }

        if (node.key < key) {
            return search(node.right, key);
        } else if (node.key > key) {
            return search(node.left, key);
        } else {
            return node;
        }
    }

    /**
     * 查找红黑树中或键值最接近key的节点
     */
    public RBTNode search(int key) {
        return search(mRoot, key);
    }

    /*
     * 查找最小结点：返回node为根结点的红黑树的最小结点。
     */
    private RBTNode minimum(RBTNode node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * 查找最小节点的key值
     * 
     * @return
     */
    public int minimum() {
        RBTNode node = minimum(mRoot);
        if (node != null) {
            return node.key;
        }
        return Integer.MIN_VALUE;
    }

    /*
     * 查找最大结点：返回node为根结点的红黑树的最大结点。
     */
    private RBTNode maximum(RBTNode node) {
        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 查找最大节点的key值
     * 
     * @return
     */
    public int maximum() {
        RBTNode node = maximum(mRoot);
        if (node != null) {
            return node.key;
        }
        return Integer.MIN_VALUE;
    }

    /*
     * 找结点(node)的后继结点。即，查找"红黑树中数据值大于该结点"的"最小结点"。
     */
    public RBTNode successor(RBTNode node) {
        // 如果node存在右孩子，则"node的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (node.right != null) {
            return minimum(node.right);
        }

        // 如果node没有右孩子。则node有以下两种可能：
        // (01) node是"一个左孩子"，则"node的后继结点"为 "它的父结点"。
        // (02) node是"一个右孩子"，则查找"node的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"node的后继结点"。
        RBTNode parentNode = node.parent;
        while ((parentNode != null) && (node == parentNode.right)) {
            node = parentNode;
            parentNode = parentNode.parent;
        }

        return parentNode;
    }

    /*
     * 找结点(x)的前驱结点。即，查找"红黑树中数据值小于该结点"的"最大结点"。
     */
    public RBTNode predecessor(RBTNode node) {
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (node.left != null) {
            return maximum(node.left);
        }

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        RBTNode parentNode = node.parent;
        while ((parentNode != null) && (node == parentNode.left)) {
            node = parentNode;
            parentNode = parentNode.parent;
        }

        return parentNode;
    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)： 
     * 
     *          px                            px 
     *         /                             / 
     *        x                             y 
     *       / \          ----->           / \ 
     *     lx   y                         x   ry 
     *         / \                       / \ 
     *       ly   ry                   lx   ly
     *
     *
     */
    private void leftRotate(RBTNode x) {
        // 设置x的右孩子为y
        RBTNode y = x.right;

        // 将 “y的左孩子” 设为 “x的右孩子”；
        // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        // 将 “x的父亲” 设为 “y的父亲”
        y.parent = x.parent;

        if (x.parent == null) {
            this.mRoot = y; // 如果 “x的父亲” 是空节点，则将y设为根节点
        } else {
            if (x.parent.left == x)
                x.parent.left = y; // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            else
                x.parent.right = y; // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
        }

        // 将 “x” 设为 “y的左孩子”
        y.left = x;
        // 将 “x的父节点” 设为 “y”
        x.parent = y;
    }

    /* 
    * 对红黑树的节点(y)进行右旋转
    *
    * 右旋示意图(对节点y进行左旋)：
    *            py                               py
    *           /                                /
    *          y                                x                  
    *         / \           ----->             / \                     
    *        x   ry                          lx   y  
    *       / \                                  / \                   
    *     lx   rx                              rx   ry
    * 
    */
    private void rightRotate(RBTNode y) {
        // 设置x是当前节点的左孩子。
        RBTNode x = y.left;

        // 将 “x的右孩子” 设为 “y的左孩子”；
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;

        // 将 “y的父亲” 设为 “x的父亲”
        x.parent = y.parent;

        if (y.parent == null) {
            this.mRoot = x; // 如果 “y的父亲” 是空节点，则将x设为根节点
        } else {
            if (y == y.parent.right)
                y.parent.right = x; // 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
            else
                y.parent.left = x; // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
        }

        // 将 “y” 设为 “x的右孩子”
        x.right = y;

        // 将 “y的父节点” 设为 “x”
        y.parent = x;
    }

    /*
     * 新建结点(key)，并将其插入到红黑树中
     *
     * 参数说明： key 插入结点的键值
     */
    public void insert(int key) {
        RBTNode node = new RBTNode(key, RED, null, null, null);

        // 如果新建结点失败，则返回。
        if (node != null) {
            insert(node);
        }
    }

    /**
     * 将一个节点插入到红黑树中。仅{@link #insert(int)}使用
     * 
     * @param node 需要插入的节点，其颜色需为红色
     */
    private void insert(RBTNode node) {
        RBTNode xNode = mRoot;
        RBTNode yNode = null;

        // 寻找合适的父节点yNode
        while (xNode != null) {
            yNode = xNode;
            if (node.key < xNode.key) {
                xNode = xNode.left;
            } else {
                xNode = xNode.right;
            }
        }

        // 插入节点。
        // 若树为空，则此时的yNode为空，插入的节点为根节点
        node.parent = yNode;
        if (yNode != null) {
            if (node.key < yNode.key) {
                yNode.left = node;
            } else {
                yNode.right = node;
            }
        } else {
            mRoot = node;
            node.color = BLACK; // 根节点设置为黑色
        }

        // 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    /*
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数； 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明： node 插入的结点
     */
    private void insertFixUp(RBTNode node) {
        RBTNode parent, gparent; // 父节点、祖父节点

        // 若“父节点存在，并且父节点的颜色是红色”
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);

            // 若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBTNode uncle = gparent.right;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RBTNode tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else { // 若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBTNode uncle = gparent.left;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    RBTNode tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        // 将根节点设为黑色
        setBlack(mRoot);
    }

    /*
     * 删除结点
     *
     */
    public void remove(int key) {
        RBTNode node;

        if ((node = search(mRoot, key)) != null)
            remove(node);
    }

    /*
     * 删除结点
     *
     * 参数说明： node 删除的结点
     */
    private void remove(RBTNode node) {
        RBTNode child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ((node.left != null) && (node.right != null)) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBTNode replace = node;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(node) != null) {
                if (parentOf(node).left == node)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.mRoot = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child != null)
                    setParent(child, parent);
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return;
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child != null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent != null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.mRoot = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }

    /*
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数； 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明： node 待修正的节点
     */
    private void removeFixUp(RBTNode node, RBTNode parent) {
        RBTNode other;

        while ((node == null || isBlack(node)) && (node != this.mRoot)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right == null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left == null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }

        if (node != null)
            setBlack(node);
    }

    /*
     * 销毁红黑树
     */
    private void destroy(RBTNode tree) {
        if (tree == null)
            return;

        if (tree.left != null) {
            destroy(tree.left);
        }
        if (tree.right != null) {
            destroy(tree.right);
        }

        tree = null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"红黑树"
     *
     * key -- 节点的键值 direction -- 0，表示该节点是根节点; -1，表示该节点是它的父结点的左孩子; 1，表示该节点是它的父结点的右孩子。
     */
    private void print(RBTNode tree, int key, int direction) {

        if (tree != null) {

            if (direction == 0) // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree) ? "R" : "B", key,
                        direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null) {
            print(mRoot, mRoot.key, 0);
        }
    }

    public class RBTNode {
        boolean color; // 颜色
        int key; // 关键字(键值)
        RBTNode left; // 左孩子
        RBTNode right; // 右孩子
        RBTNode parent; // 父结点

        public RBTNode(int key, boolean color, RBTNode parent, RBTNode left, RBTNode right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }
}