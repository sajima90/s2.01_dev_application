#!/bin/bash

echo "Choisissez une option de lancement :"
echo "1) Lancement classique aléatoire"
echo "2) Lancement d'un scénario spécifique"

read -p "Entrez votre choix (1 ou 2) : " choix

case $choix in
    1)
        java psyche.Controleur
        ;;
    2)
        read -p "Entrez le nom du scénario (ex: scenario_1.run) : " scenario
        if [[ -f "$scenario" ]]; then
            java psyche.Controleur "$scenario"
        else
            echo "Le fichier de scénario '$scenario' n'existe pas."
        fi
        ;;
    *)
        echo "Choix invalide. Veuillez entrer 1 ou 2."
        ;;
esac