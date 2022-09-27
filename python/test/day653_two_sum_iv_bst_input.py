from typing import Optional, Set

from python.test.model.tree_node import TreeNode
from python.test.model.tree_parser import parse_tree


def test_two_sum_bst():
    result = find_target(parse_tree([5, 3, 6, 2, 4, None, 7]), 9)
    assert result == True


def find_target(root: Optional[TreeNode], k: int) -> bool:
    visited = set()
    return do_find_target(root, k, visited)


def do_find_target(root: Optional[TreeNode], k: int, visited: Set[TreeNode]) -> bool:
    if root is None:
        return False
    diff = k - root.val
    if diff in visited:
        return True
    visited.add(root.val)

    return do_find_target(root.left, k, visited) or do_find_target(root.right, k, visited)
