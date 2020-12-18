package ua.edu.sumdu.j2se.kvytnitskyi.tasks;

public class ArrayTaskList
{
    private static final int SIZE_ARRAY = 5;

    private int fill;
    private Task[] tasks;

    public ArrayTaskList () { tasks = new Task[SIZE_ARRAY]; }

    public void add (Task task)
    {
        if (task == null)
            throw new NullPointerException("Task object parameter has null value!");

        if (tasks.length == fill)
        {
            Task[] tempTasks = new Task[fill * 2];
            System.arraycopy(tasks, 0, tempTasks, 0, fill);
            tasks = tempTasks;
        }
        tasks[fill] = task;
        fill++;
    }

    public boolean remove (Task task)
    {
        if (task == null)
            throw new NullPointerException("Task object parameter has null value!");

        int i = 0;

        while ((tasks[i] != task) && (i != size()))
            i++;

        if (i == size())
        {
            return false;
        } else {
            fill--;
            if (size() - i >= 0) System.arraycopy(tasks, i + 1, tasks, i, size() - i);
            return true;
        }
    }

    public int size () { return fill; }

    public Task getTask (int index)
    {
        if (index < 0 || index > fill)
            throw new IndexOutOfBoundsException("Invalid ArrayTaskList index!");

        return tasks[index];
    }

    public ArrayTaskList incoming(int from, int to)
    {
        if (from > to)
            throw new IllegalArgumentException("Invalid interval parameters!");

        ArrayTaskList result = new ArrayTaskList();
        for (int i = 0; i < fill; i++)
        {
            if ((tasks[i].nextTimeAfter(from) > from) && (tasks[i].nextTimeAfter(from) <= to))
                result.add(tasks[i]);
        }
        return result;
    }
}
