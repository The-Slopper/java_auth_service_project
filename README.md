# r3-java-auth (Rodada 3 — avançada)

Verificador de JWT e gestão de sessão em Java. Valida tokens, claims e renova
sessões após login.

## Compilando

```bash
javac TokenVerifier.java && java TokenVerifier
```

Bugs sutis de verificação de JWT (confusão de algoritmo, claims, comparação de
assinatura) e ciclo de vida de sessão.
