#coding:utf-8

from itertools import combinations, groupby
import networkx as nx
import random
import matplotlib.pyplot as plt
import numpy as np

def gnp_random_connected_graph(n, p):
    """
    Generates a random undirected graph, similarly to an Erdos-Renyi
    graph, but enforcing that the resulting graph is connected
    """
    assert p > 0 and p <= 1
    edges = combinations(range(n), 2)
    G = nx.Graph()
    G.add_nodes_from(range(n))
    if p >= 1:
        return nx.complete_graph(n, create_using=G)
    for _, node_edges in groupby(edges, key=lambda x: x[0]):
        node_edges = list(node_edges)
        random_edge = random.choice(node_edges)
        G.add_edge(*random_edge)
        for e in node_edges:
            if random.random() < p:
                G.add_edge(*e)
    for (u, v) in G.edges():
        G.edges[u,v]['weight'] = random.randint(1,100)
    return G

def Dijkstra_networkX(order, density):
    G = gnp_random_connected_graph(order,density)
    nx.draw(G, node_color='lightblue', with_labels=True, node_size=500)
    plt.show()
    Way_size = nx.shortest_path_length(G, source=0, target=G.order()-1, method='dijkstra')
    print("shortest way is ", Way_size)

def main():
    Dijkstra_networkX(10, 0.05)

if __name__ == '__main__':
    main()
