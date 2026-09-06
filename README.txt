This repository contains the Korat testing tool, originally version 1.0
released in April 2007, updated to build and run on Java 17.
This is a modified fork; see CHANGES.md for the dated change summary.

KORAT
=====

Korat is a tool for constraint-based generation of structurally
complex test inputs for Java programs.  Structurally complex means
that the inputs are structural (e.g., represented with linked data
structures) and must satisfy complex constraints that relate parts of
the structure (e.g., invariants for linked data structures).

Korat requires (1) an imperative predicate that specifies the desired
structural constraints and (2) a finitization that bounds the desired
test input size.  Korat generates all predicate inputs (within the
bounds) for which the predicate returns true.  To do so, Korat
performs a systematic search of the predicate's input space.  The
inputs that Korat generates enable bounded-exhaustive testing for
programs ranging from library classes to stand-alone applications.

More info about Korat is available at http://mir.cs.uiuc.edu/korat.

INSTALLATION
============

Requirements: JDK 17 and Apache Ant 1.10.2 or later (latest 1.10.x recommended).
The bundled Javassist library is version 3.30.2-GA; see lib/DEPENDENCIES.txt.

From the directory containing build.xml:
  ant                 # compile into build/ (the default target)
  ant test            # run all 53 tests, without installing JUnit globally
  ant createJar       # rebuild and package dist/korat.jar and dist/lib/
  ant help            # list build targets

Run the packaged binary:
  java -jar dist/korat.jar --class korat.examples.binarytree.BinaryTree --args 3

This example generates 5 valid structures. Keep the entire dist/ directory
together, including lib/, README.txt and LICENSE.txt. No --add-opens or --add-exports flags are required.
The build emits Java 17 bytecode; the old Java 1.4 build targets were removed.

EXAMPLES
========

From the repository root, add the compiled classes and bundled libraries:
  export KORAT_HOME="$PWD"
  export KORAT_CP="$KORAT_HOME/build:$KORAT_HOME/lib/*"

Alternatively, after ant createJar:
  export KORAT_CP="$KORAT_HOME/dist/korat.jar:$KORAT_HOME/dist/lib/*"

You can then run some examples such as these:

  # Generate binary trees with 3 nodes; should generate 5
  java -classpath "$KORAT_CP" korat.Korat \
    --class korat.examples.binarytree.BinaryTree --args 3
    
  # Generate binary search trees with 3 nodes; should generate 5
  java -classpath "$KORAT_CP" korat.Korat \
    --class korat.examples.searchtree.SearchTree --args 3

  # Generate singly linked lists of size 3; should generate 5
  java -classpath "$KORAT_CP" korat.Korat \
    --class korat.examples.singlylinkedlist.SinglyLinkedList --args 3

  # Generate doubly linked lists of size 3; should generate 10
  java -classpath "$KORAT_CP" korat.Korat \
    --class korat.examples.doublylinkedlist.DoublyLinkedList --args 3

  # Generate red-black trees with 7 nodes; should generate 33
  java -classpath "$KORAT_CP" korat.Korat \
    --class korat.examples.redblacktree.RedBlackTree --args 7

  # Generate heaps (priority queues) represented with arrays; should generate 117562
  java -classpath "$KORAT_CP" korat.Korat \
    --class korat.examples.heaparray.HeapArray --args 7
    
INSTRUCTIONS
============

Until we write a user's manual, the best ways to learn how to use
Korat are to look at the examples in the korat.examples.* packages
or to read some paper on Korat from <http://mir.cs.uiuc.edu/korat>.
You should also feel free to contact the Korat team (see below).

VISUALIZATION
=============

Korat can graphically show the structures it generates.  The
visualization in Korat was inspired by Alloy <http://alloy.mit.edu>,
and our current Korat implementation uses the Alloy Analyzer's
visualization facility, which provides a fully customizable display
that allows users to specify desired views on the underlying
structures.  Korat automatically translates object graphs into the
Alloy representation.

Visualization uses the bundled Alloy 6.2.0 distribution,
lib/org.alloytools.alloy.dist.jar. The old Alloy 4 library is retained in
lib/legacy/ for reference and must not be added to the classpath.
Alloy 6 provides its own graph renderer; no external GraphViz installation
is needed. A graphical desktop session is required.

Build and launch the visualizer on Java 17:
  ant createJar
  java -jar dist/korat.jar --visualize --class korat.examples.binarytree.BinaryTree --args 3

The five generated instances are available in Alloy's Window menu.
Korat writes Alloy 6 XML to viz_instances/ and themes to viz_themes/;
previously generated XML should be regenerated with this version.

To instruct Korat to visualize the generated structures, you need to
add switch "--visualize" to the command-line arguments such as these:

  java -classpath "$KORAT_CP" korat.Korat \
    --visualize --class korat.examples.binarytree.BinaryTree --args 3
  
  java -classpath "$KORAT_CP" korat.Korat \
    --visualize \
    --class korat.examples.singlylinkedlist.SinglyLinkedList --args 3
  
  java -classpath "$KORAT_CP" korat.Korat \
    --visualize --class korat.examples.fibheap.FibonacciHeap --args 2

LICENSE
=======

The source code of Korat is distributed under the GNU General Public
License version 2 (see LICENSE.txt).  Korat also uses several
third-party packages whose code may be distributed under different
licenses (see the appropriate LICENSE files in the lib directory for
details).

See lib/DEPENDENCIES.txt for the dependency inventory and the Alloy license
notice clarification. Original license texts are preserved unchanged;
legacy JARs and their licenses are retained in lib/legacy/. The packaged
distribution includes active dependency license files and the dependency
inventory. Alloy component notices remain inside the supplied JAR.

CONTACT
=======

To contact the Korat team, see http://mir.cs.uiuc.edu/korat.

ACKNOWLEDGMENTS
===============

Jeremy Boyd (UT Austin) transferred the repository from SVN to GIT in
August 2015
