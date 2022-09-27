from collections import deque
from typing import List

from python.test.model.tree_node import TreeNode


def parse_tree(data: List[int]) -> TreeNode:
    root = TreeNode(data[0])
    index = 1
    nodes_to_process = deque([root])
    while nodes_to_process:
        current_node = nodes_to_process.popleft()
        if index < len(data):
            val_left = data[index]
            index += 1
            if val_left is not None:
                left = TreeNode(val_left)
                left.parent = current_node
                current_node.left = left
                nodes_to_process.append(left)
        if index < len(data):
            val_right = data[index]
            index += 1

            if val_right is not None:
                right = TreeNode(val_right)
                right.parent = current_node
                current_node.right = right
                nodes_to_process.append(right)

    return root
