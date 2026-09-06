# Java 17 modernization — 2026-09-06

This is a modified version of the original Korat distribution. Original
copyright and license notices are preserved. The existing repository history
records the imported source; this file describes the modernization changes.

- Build Java 17 bytecode with Ant; make compilation the default target.
- Update Javassist to 3.30.2-GA and use supported generated-class loading.
- Correct generated array initialization and exclude JDK internals.
- Integrate the supplied Alloy 6.2.0 distribution and its XML visualization format.
- Require a black root in the red-black tree invariant; retain the original
  key domain and visited-node handling. Size 5 explores 2,968 candidates
  and produces 8 valid trees.
- Replace deprecated primitive-wrapper constructors with valueOf calls.
- Run 53 regression tests through Ant without a global JUnit installation.
- Package runtime libraries, README, project license, and dependency license files.
- Preserve replaced libraries and their original licenses in lib/legacy/.
- Update IDE classpath configuration for Java 17 and Alloy 6.
