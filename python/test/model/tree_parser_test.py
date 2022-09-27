from python.test.model.tree_parser import parse_tree


def test_parse_input():
    data = [3, 9, 20, None, None, 15, 7]
    root = parse_tree(data)
    assert root.val == 3
    assert root.left.val == 9
    assert root.left.left is None
    assert root.left.right is None
    assert root.right.val == 20
    assert root.right.left.val == 15
    assert root.right.left.left is None
    assert root.right.left.right is None
    assert root.right.right.val == 7
    assert root.right.right.left is None
    assert root.right.right.right is None