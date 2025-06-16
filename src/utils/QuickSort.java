package utils;

import domain.entities.Inscricao;

public class QuickSort {
    
    public static void sort(List<Inscricao> list) throws Exception {
        if (list == null || list.isEmpty()) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<Inscricao> list, int low, int high) throws Exception {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<Inscricao> list, int low, int high) throws Exception {
        Inscricao pivot = list.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            Inscricao current = list.get(j);
            
            // Compare professor scores
            if (current.getProfessor().getPontuacao() >= pivot.getProfessor().getPontuacao()) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Inscricao> list, int i, int j) throws Exception {
        Inscricao temp = list.get(i);
        Inscricao element1 = list.get(i);
        Inscricao element2 = list.get(j);
        
        list.remove(i);
        list.add(element2, i);
        list.remove(j);
        list.add(element1, j);
    }
}
