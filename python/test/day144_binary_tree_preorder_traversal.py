from typing import Optional, List

from python.test.model.tree_node import TreeNode
from python.test.model.tree_parser import parse_tree


def test_preorder_traversal():
    result = preorder_traversal(parse_tree([1, None, 2, 3]))
    assert result == [1, 2, 3]


def preorder_traversal(root: Optional[TreeNode]) -> List[int]:
    def traverse(node: Optional[TreeNode], res: List[int]):
        if node is None:
            return

        res.append(node.val)
        traverse(node.left, res)
        traverse(node.right, res)

    res = []
    traverse(root, res)
    return res
