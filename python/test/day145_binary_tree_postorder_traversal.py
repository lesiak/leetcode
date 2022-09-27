from typing import Optional, List

from python.test.model.tree_node import TreeNode
from python.test.model.tree_parser import parse_tree


def test_postorder_traversal():
    result = postorder_traversal(parse_tree([1, None, 2, 3]))
    assert result == [3, 2, 1]


def postorder_traversal(root: Optional[TreeNode]) -> List[int]:
    def traverse(node: Optional[TreeNode], res: List[int]):
        if node is None:
            return

        traverse(node.left, res)
        traverse(node.right, res)
        res.append(node.val)

    res = []
    traverse(root, res)
    return res
